package com.leuan.lepton.order.order.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.ChatBotUtils
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.config.service.ConfigService
import com.leuan.lepton.framework.dict.dal.QDictItem
import com.leuan.lepton.order.order.controller.dto.OrderNotifyDTO
import com.leuan.lepton.order.order.controller.dto.OrderQueryDTO
import com.leuan.lepton.order.order.controller.dto.OrderSaveDTO
import com.leuan.lepton.order.order.controller.vo.OrderVO
import com.leuan.lepton.order.order.dal.Order
import com.leuan.lepton.order.order.dal.OrderRepository
import com.leuan.lepton.order.order.dal.QOrder
import com.leuan.lepton.order.order.mapping.OrderMapper
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * 订单服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[OrderService]
 */
@Service
class OrderService {

    @Resource
    private lateinit var orderMapper: OrderMapper

    @Resource
    private lateinit var orderRepository: OrderRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var chatBotUtils: ChatBotUtils

    @Resource
    private lateinit var configService: ConfigService

    private val qOrder = QOrder.order

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [OrderVO]
     */
    fun getById(id: Long): OrderVO {
        val entity = orderRepository
            .findOne(qOrder.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.ORDER_NOT_FOUND) }
        return orderMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<OrderVO>]
     */
    fun list(queryDTO: OrderQueryDTO): List<OrderVO> {
        return jpaQueryFactory
            .selectFrom(qOrder)
            .buildExpressions(queryDTO)
            .fetch()
            .map(orderMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<OrderVO>]
     */
    fun page(queryDTO: OrderQueryDTO): PageDTO<OrderVO> {
        val pageDTO = PageDTO<OrderVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qOrder)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qOrder.id.count()).from(qOrder).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(orderMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [orderSaveDTO] 订单保存传输层对象
     * @return [OrderVO]
     */
    fun save(orderSaveDTO: OrderSaveDTO): OrderVO {
        val entity = orderSaveDTO.id?.let {
            orderRepository.findById(it).orElseThrow { BizErr(BizErrEnum.ORDER_NOT_FOUND) }
        } ?: Order()

        orderMapper.partialUpdate(orderSaveDTO, entity)
        orderRepository.save(entity)

        if (orderSaveDTO.id == null) {
            sendNotify(entity.id)
        }
        return orderMapper.toVO(entity)
    }

    fun sendNotify(orderId: Long) {
        val photoTypeDict = QDictItem("photoTypeDict")
        val sourceDict = QDictItem("sourceDict")
        val orderNotifyDTO = jpaQueryFactory.select(
            Projections.fields(
                OrderNotifyDTO::class.java,
                qOrder.id,
                qOrder.customer.name.`as`(OrderNotifyDTO::customerName.name),
                qOrder.customer.phone.`as`(OrderNotifyDTO::customerPhone.name),
                qOrder.customer.wechat.`as`(OrderNotifyDTO::customerWechat.name),
                qOrder.customer.channel.name.`as`(OrderNotifyDTO::customerChannelName.name),
                qOrder.goods.name.`as`(OrderNotifyDTO::goodsName.name),
                qOrder.goods.price.`as`(OrderNotifyDTO::goodsPrice.name),
                qOrder.payAmount.`as`(OrderNotifyDTO::payAmount.name),
                qOrder.seller.name.`as`(OrderNotifyDTO::sellerName.name)
            )
        ).from(qOrder)
            .leftJoin(photoTypeDict).on(
                photoTypeDict.dict.type.eq("customer:photo_type").and(photoTypeDict.value.eq(qOrder.customer.photoType))
            )
            .leftJoin(sourceDict)
            .on(sourceDict.dict.type.eq("customer:source").and(sourceDict.value.eq(qOrder.customer.source)))
            .where(qOrder.id.eq(orderId))
            .fetchOne()!!

        val currentDate = LocalDate.now()
        val monthOrderCount = jpaQueryFactory.select(qOrder.id.count())
            .from(qOrder)
            .where(qOrder.createdAt.year().eq(currentDate.year), qOrder.createdAt.month().eq(currentDate.monthValue))
            .fetchOne()!!

        val dayOrderCount = jpaQueryFactory.select(qOrder.id.count())
            .from(qOrder)
            .where(
                qOrder.createdAt.year().eq(currentDate.year),
                qOrder.createdAt.month().eq(currentDate.monthValue),
                qOrder.createdAt.dayOfMonth().eq(currentDate.dayOfMonth)
            )
            .fetchOne()!!
        val tenantConfig = configService.getConfig().tenantConfig
        val message = """
            [订单通知]
            
            编号: ${tenantConfig.orderTarget}-${monthOrderCount}-${dayOrderCount}
            客户姓名：${orderNotifyDTO.customerName}
            客户电话：${orderNotifyDTO.customerPhone ?: ""}
            客户微信：${orderNotifyDTO.customerWechat ?: ""}
            
            渠道：${orderNotifyDTO.customerChannelName}
            商品：${orderNotifyDTO.goodsName}
            商品价格：${orderNotifyDTO.goodsPrice / 100.0}
            实付金额：${orderNotifyDTO.payAmount / 100.0}
            销售：${orderNotifyDTO.sellerName}
        """.trimIndent()

        chatBotUtils.sendNotify(
            tenantConfig?.orderNotifyUrl,
            message
        )
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        orderRepository.deleteById(id)
        return true
    }

}