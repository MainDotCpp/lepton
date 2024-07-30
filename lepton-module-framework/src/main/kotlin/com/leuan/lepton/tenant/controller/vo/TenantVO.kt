package com.leuan.lepton.tenant.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.tenant.dal.Tenant }
 */
@NoArgsConstructor
data class TenantVO(
    var id: Long
) : Serializable