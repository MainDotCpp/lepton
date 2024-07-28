package com.leuan.lepton.tenant.controller.dto

import com.leuan.lepton.syspackage.dal.SysPackage
import com.leuan.lepton.tenant.enums.TenantTypeEnum

/**
 * DTO for {@link com.leuan.lepton.tenant.dal.Tenant}
 */
data class TenantSaveDTO(
    val id: Long? = null,
    val name: String = "", val type: TenantTypeEnum = TenantTypeEnum.ACTIVE, val sysPackage: SysPackage? = null
)
