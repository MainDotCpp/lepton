package com.leuan.lepton.customer.customer.mapping

import com.leuan.lepton.common.mapping.LeptonBaseMapping
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.Customer
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = [LeptonBaseMapping::class]
)
abstract class CustomerMapper {
    @org.mapstruct.Mappings(
        org.mapstruct.Mapping(source = "createdBy.id", target = "createdById"),
        org.mapstruct.Mapping(source = "createdBy.name", target = "createdByName")
    )
    abstract fun toVO(customer: Customer): CustomerVO
    abstract fun toEntity(customerSaveDTO: CustomerSaveDTO): Customer
    abstract fun saveDtoToEntity(customerSaveDTO: CustomerSaveDTO): Customer
}