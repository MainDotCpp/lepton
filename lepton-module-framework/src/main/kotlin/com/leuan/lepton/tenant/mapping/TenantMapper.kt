package com.leuan.lepton.tenant.mapping

import com.leuan.lepton.common.mapping.LeptonBaseMapping
import com.leuan.lepton.tenant.controller.dto.TenantQueryDTO
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
    @org.mapstruct.Mappings(org.mapstruct.Mapping(source = "packageId", target = "sysPackage.id"), org.mapstruct.Mapping(source = "packageName", target = "sysPackage.name"))
    abstract fun toEntity(tenantVO: TenantVO): Tenant
    @org.mapstruct.Mappings(org.mapstruct.Mapping(source = "sysPackage.id", target = "packageId"), org.mapstruct.Mapping(source = "sysPackage.name", target = "packageName"))
    abstract fun toVO(tenant: Tenant): TenantVO


    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: TenantSaveDTO, @MappingTarget tenant: Tenant): Tenant

    fun idToEntity(id: Long?): Tenant? {
        return id?.let { Tenant().apply { this.id = it } }
    }

}