package com.leuan.lepton.tenant.mapping

import com.leuan.lepton.common.mapping.LeptonBaseMapping
import com.leuan.lepton.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.tenant.controller.vo.TenantVO
import com.leuan.lepton.tenant.dal.Tenant
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class TenantMapper {
    abstract fun toEntity(tenantVO: TenantVO): Tenant
    abstract fun toVO(tenant: Tenant): TenantVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: TenantSaveDTO, @MappingTarget tenant: Tenant): Tenant

    fun idToEntity(id: Long?): Tenant? {
        return id?.let { Tenant().apply { this.id = it } }
    }

}