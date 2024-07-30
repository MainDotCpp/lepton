package com.leuan.lepton.tenant.mapping

import com.leuan.lepton.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.tenant.controller.vo.TenantVO
import com.leuan.lepton.tenant.dal.Tenant
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class TenantMapper {
    @org.mapstruct.Mappings(
        org.mapstruct.Mapping(source = "sysPackageId", target = "sysPackage.id"),
        org.mapstruct.Mapping(source = "sysPackageName", target = "sysPackage.name")
    )
    abstract fun toEntity(tenantVO: TenantVO): Tenant

    @org.mapstruct.Mappings(
        org.mapstruct.Mapping(source = "sysPackage.id", target = "sysPackageId"),
        org.mapstruct.Mapping(source = "sysPackage.name", target = "sysPackageName")
    )
    abstract fun entityToVO(tenant: Tenant): TenantVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(tenantVO: TenantSaveDTO, @MappingTarget tenant: Tenant): Tenant

    abstract fun saveDtoToEntity(tenantSaveDTO: TenantSaveDTO): Tenant
}