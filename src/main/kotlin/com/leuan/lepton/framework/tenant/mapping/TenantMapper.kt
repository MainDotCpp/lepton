package com.leuan.lepton.framework.tenant.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.syspackage.mapping.SysPackageMapper
import com.leuan.lepton.framework.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.framework.tenant.controller.vo.TenantVO
import com.leuan.lepton.framework.tenant.dal.Tenant
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class, SysPackageMapper::class]
)
abstract class TenantMapper {
    @org.mapstruct.Mappings(
        org.mapstruct.Mapping(source = "packageId", target = "sysPackage.id"),
        org.mapstruct.Mapping(source = "packageName", target = "sysPackage.name")
    )
    abstract fun toEntity(tenantVO: TenantVO): Tenant

    @org.mapstruct.Mappings(
        org.mapstruct.Mapping(source = "sysPackage.id", target = "packageId"),
        org.mapstruct.Mapping(source = "sysPackage.name", target = "packageName")
    )
    abstract fun toVO(tenant: Tenant): TenantVO


    @Mapping(source = "packageId", target = "sysPackage")
    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: TenantSaveDTO, @MappingTarget tenant: Tenant): Tenant

    fun idToEntity(id: Long?): Tenant? {
        return id?.let { Tenant().apply { this.id = it } }
    }

}