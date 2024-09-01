package com.leuan.lepton.customer.brand.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.customer.brand.controller.dto.BrandSaveDTO
import com.leuan.lepton.customer.brand.controller.dto.BrandQueryDTO
import com.leuan.lepton.customer.brand.controller.vo.BrandVO
import com.leuan.lepton.customer.brand.dal.Brand
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class BrandMapper {
    abstract fun toVO(brand: Brand): BrandVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: BrandSaveDTO, @MappingTarget brand: Brand): Brand

    fun idToEntity(id: Long?): Brand? {
        return id?.let { Brand().apply { this.id = it } }
    }

    fun entityToId(brand: Brand?): Long? {
        return brand?.id
    }

}