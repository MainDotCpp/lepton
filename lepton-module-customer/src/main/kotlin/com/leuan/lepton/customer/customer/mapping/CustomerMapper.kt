package com.leuan.lepton.customer.customer.mapping

import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.Customer
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class CustomerMapper {
    abstract fun toEntity(customerVO: CustomerVO): Customer
    abstract fun entityToVO(customer: Customer): CustomerVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(customerVO: CustomerVO, @MappingTarget customer: Customer): Customer

    abstract fun saveDtoToEntity(customerSaveDTO: CustomerSaveDTO): Customer
}