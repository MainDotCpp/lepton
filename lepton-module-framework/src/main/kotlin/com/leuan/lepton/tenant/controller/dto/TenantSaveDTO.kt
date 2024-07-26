package com.leuan.lepton.tenant.controller.dto

/**
 * DTO for {@link com.leuan.lepton.tenant.dal.Tenant}
 */
data class TenantSaveDTO(
    val id: Long? = null,
    val name: String = ""
)
