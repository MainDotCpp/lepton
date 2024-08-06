package com.leuan.lepton.customer.customer.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.QCustomer
import com.leuan.lepton.customer.customer.dal.Customer
import com.leuan.lepton.customer.customer.dal.CustomerRepository
import com.leuan.lepton.customer.customer.mapping.CustomerMapper
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


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<CustomerVO>]
     */
    fun list(queryDTO: CustomerQueryDTO): List<CustomerVO> {
        return jpaQueryFactory
            .selectFrom(qCustomer)
            .buildExpressions(queryDTO)
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
            .buildExpressions(queryDTO)
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
        return customerMapper.toVO(entity)
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