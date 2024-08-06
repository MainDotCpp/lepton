package com.leuan.lepton.customer.customer.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.Customer
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class CustomerMapper {
    abstract fun toEntity(customerVO: CustomerVO): Customer
    abstract fun toEntity(customerQueryDTO: CustomerQueryDTO): Customer
    abstract fun toVO(customer: Customer): CustomerVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: CustomerSaveDTO, @MappingTarget customer: Customer): Customer

    fun idToEntity(id: Long?): Customer? {
        return id?.let { Customer().apply { this.id = it } }
    }

    fun entityToId(customer: Customer?): Long? {
        return customer?.id
    }

}