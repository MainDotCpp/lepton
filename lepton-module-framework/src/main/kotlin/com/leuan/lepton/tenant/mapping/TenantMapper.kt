package com.leuan.lepton.tenant.mapping

import com.leuan.lepton.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.tenant.controller.vo.TenantVO
import com.leuan.lepton.tenant.dal.Tenant
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class TenantMapper {
    abstract fun toEntity(tenantVO: TenantVO): Tenant
    abstract fun entityToVO(tenant: Tenant): TenantVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(tenantVO: TenantVO, @MappingTarget tenant: Tenant): Tenant

    abstract fun saveDtoToEntity(tenantSaveDTO: TenantSaveDTO): Tenant
}