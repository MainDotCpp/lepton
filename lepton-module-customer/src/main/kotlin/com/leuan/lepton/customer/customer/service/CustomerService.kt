package com.leuan.lepton.customer.customer.service

import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.CustomerRepository
import com.leuan.lepton.customer.customer.dal.QCustomer
import com.leuan.lepton.customer.customer.mapping.CustomerMapper
import com.leuan.lepton.user.dal.QUser
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    /**
     * 通过id获取
     * @param [id] ID
     * @return [CustomerVO]
     */
    fun getById(id: Long): CustomerVO {
        val customer = customerRepository.findOne(QCustomer.customer.id.eq(id)).get()
        logInfo(customer.createdBy?.name ?: "cc")
        return customerMapper.toVO(customer)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: CustomerQueryDTO) = arrayOf(
        queryDTO.id?.let { QCustomer.customer.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<CustomerVO>]
     */
    @Transactional(readOnly = true)
    fun list(queryDTO: CustomerQueryDTO): List<CustomerVO> {
        val expressions = buildExpressions(queryDTO)
        val res = jpaQueryFactory
            .selectFrom(QCustomer.customer)
            .leftJoin(QUser.user).on(QCustomer.customer.createdBy.eq(QUser.user)).fetchJoin()
            .where(*expressions)
            .fetch()
        logInfo("获取客资列表|查询条件：${queryDTO.toJson()}|结果：${res}")
        return res.map(customerMapper::toVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<CustomerVO>]
     */
    fun page(queryDTO: CustomerQueryDTO): PageDTO<CustomerVO> {
        val pageDTO = PageDTO<CustomerVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(QCustomer.customer)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(QCustomer.customer.id.count()).from(QCustomer.customer).where(*expressions)
                .fetchOne() ?: 0
        pageDTO.records = query.fetch().map(customerMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [customerSaveDTO] 客资保存传输层对象
     * @return [CustomerVO]
     */
    fun save(customerSaveDTO: CustomerSaveDTO): CustomerVO {
        val entity = customerSaveDTO.id?.let {
            customerRepository.findById(it).orElseThrow()
        } ?: customerMapper.saveDtoToEntity(customerSaveDTO)
        customerRepository.save(entity)
        logInfo("保存客资成功|${entity.toJson()}")
        return customerMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除客资|ID：$id")
        customerRepository.deleteById(id)
        return true
    }

}