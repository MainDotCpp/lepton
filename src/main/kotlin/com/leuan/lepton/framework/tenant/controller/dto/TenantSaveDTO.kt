package com.leuan.lepton.framework.tenant.controller.dto

import com.leuan.lepton.framework.tenant.enums.TenantTypeEnum
import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.framework.tenant.dal.Tenant }
 */
@NoArgsConstructor
data class TenantSaveDTO(
    val id: Long? = null,
    val logo: String = "",
    val name: String = "",
    val type: TenantTypeEnum = TenantTypeEnum.ACTIVE,
    val code: String = "",
    val packageId: Long? = null,
) {
}
