package com.leuan.lepton.customer.customer.service

import com.leuan.lepton.customer.customer.bo.CustomerNotifyBO
import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.Customer
import com.leuan.lepton.customer.customer.dal.CustomerRepository
import com.leuan.lepton.customer.customer.dal.QCustomer
import com.leuan.lepton.customer.customer.mapping.CustomerMapper
import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.log.logInfo
import com.leuan.lepton.framework.common.utils.ChatBotUtils
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.common.utils.toJson
import com.leuan.lepton.framework.config.service.ConfigService
import com.leuan.lepton.framework.dict.dal.QDictItem
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 客资服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[CustomerService]
 */
@Service
class CustomerService {

    @Resource
    private lateinit var customerMapper: CustomerMapper

    @Resource
    private lateinit var customerRepository: CustomerRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var configService: ConfigService

    @Resource
    private lateinit var chatBotUtils: ChatBotUtils

    private val qCustomer = QCustomer.customer


    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [CustomerVO]
     */
    fun getById(id: Long): CustomerVO {
        val entity = customerRepository
            .findOne(qCustomer.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.CUSTOMER_NOT_FOUND) }
        return customerMapper.toVO(entity)
    }

    fun customWhere(queryDTO: CustomerQueryDTO): Array<BooleanExpression?> {
        return arrayOf(
            queryDTO.keywords?.let {
                qCustomer.name.like("%$it%")
                    .or(qCustomer.phone.like("%$it%"))
                    .or(qCustomer.wechat.like("%$it%"))
            }
        )
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<CustomerVO>]
     */
    fun list(queryDTO: CustomerQueryDTO): List<CustomerVO> {
        return jpaQueryFactory
            .selectFrom(qCustomer)
            .buildExpressions(queryDTO) {
                customWhere(queryDTO)
            }
            .fetch()
            .map(customerMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<CustomerVO>]
     */
    fun page(queryDTO: CustomerQueryDTO): PageDTO<CustomerVO> {
        val pageDTO = PageDTO<CustomerVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qCustomer)
            .buildExpressions(queryDTO) {
                customWhere(queryDTO)
            }
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qCustomer.id.count()).from(qCustomer).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(customerMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [customerSaveDTO] 客资保存传输层对象
     * @return [CustomerVO]
     */
    fun save(customerSaveDTO: CustomerSaveDTO): CustomerVO {
        val entity = customerSaveDTO.id?.let {
            customerRepository.findById(it).orElseThrow { BizErr(BizErrEnum.CUSTOMER_NOT_FOUND) }
        } ?: Customer()

        customerMapper.partialUpdate(customerSaveDTO, entity)
        customerRepository.save(entity)

        // 首次创建客资发送机器人信息
        if (customerSaveDTO.id == null) {
            sendNotify(entity.id)
        }

        return customerMapper.toVO(entity)
    }

    fun sendNotify(customerId: Long) {
        val photoTypeDict = QDictItem("photoTypeDict")
        val sourceDict = QDictItem("sourceDict")
        val followStatusDict = QDictItem("followStatusDict")
        val customer = jpaQueryFactory
            .select(
                Projections.fields(
                    CustomerNotifyBO::class.java,
                    qCustomer.id,
                    qCustomer.name,
                    qCustomer.wechat,
                    qCustomer.phone,
                    qCustomer.remark,
                    photoTypeDict.item.`as`("photoType"),
                    sourceDict.item.`as`("source"),
                    followStatusDict.item.`as`("followStatus"),
                    qCustomer.createdBy.name.`as`("createdByName"),
                    qCustomer.sale.name.`as`("saleName"),
                )
            )
            .from(qCustomer)
            .leftJoin(photoTypeDict).on(photoTypeDict.dict.type.eq("customer:photo_type").and(qCustomer.photoType.eq(photoTypeDict.value)))
            .leftJoin(sourceDict).on(sourceDict.dict.type.eq("customer:source").and(qCustomer.source.eq(sourceDict.value)))
            .leftJoin(followStatusDict).on(followStatusDict.dict.type.eq("customer:follow_up_status").and(qCustomer.followStatus.eq(followStatusDict.value)))
            .where(qCustomer.id.eq(customerId))
            .fetchOne()!!
        logInfo("customer:${customer.toJson()}")

        val message = """
            [新客资]
            
            姓名：${customer.name}
            电话：${customer.phone ?: ""}
            微信：${customer.wechat ?: ""}
            来源：${customer.source}
            拍摄类型: ${customer.photoType}
            跟进状态: ${customer.followStatus ?: ""}
            
            销售: ${customer.saleName ?: ""}
            录入人：${customer.createdByName ?: ""}
            备注：${customer.remark ?: ""}
        """.trimIndent()
        configService.getConfig().tenantConfig?.customerNotifyUrl
            ?.let { chatBotUtils.sendNotify(it, message) }
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        customerRepository.deleteById(id)
        return true
    }

}