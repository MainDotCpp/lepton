package com.leuan.lepton.customer.customer.mapping

import com.leuan.lepton.customer.brand.mapping.BrandMapper
import com.leuan.lepton.customer.channel.mapping.ChannelMapper
import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.customer.customer.dal.Customer
import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class, ChannelMapper::class,BrandMapper::class]
)
abstract class CustomerMapper {

    @Mappings(
        Mapping(source = "brand.id", target = "brandId"),
        Mapping(source = "sale.id", target = "saleId"),
        Mapping(source = "channel.id", target = "channelId"),
        Mapping(source = "createdBy.id", target = "createdById")
    )
    abstract fun toVO(customer: Customer): CustomerVO

    @Mapping(source = "channelId", target = "channel")
    @Mapping(source = "saleId", target = "sale")
    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "createdById", target = "createdBy")
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