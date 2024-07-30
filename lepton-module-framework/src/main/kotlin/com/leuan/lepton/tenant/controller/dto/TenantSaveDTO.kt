package com.leuan.lepton.tenant.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.tenant.dal.Tenant }
 */
@NoArgsConstructor
data class TenantSaveDTO(
    val id: Long? = null
)
