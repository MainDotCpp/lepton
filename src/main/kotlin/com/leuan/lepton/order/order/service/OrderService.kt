package com.leuan.lepton.order.order.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.order.order.controller.dto.OrderQueryDTO
import com.leuan.lepton.order.order.controller.dto.OrderSaveDTO
import com.leuan.lepton.order.order.controller.vo.OrderVO
import com.leuan.lepton.order.order.dal.QOrder
import com.leuan.lepton.order.order.dal.Order
import com.leuan.lepton.order.order.dal.OrderRepository
import com.leuan.lepton.order.order.mapping.OrderMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

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
        return orderMapper.toVO(entity)
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