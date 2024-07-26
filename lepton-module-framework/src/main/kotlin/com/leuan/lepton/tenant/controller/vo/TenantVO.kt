package com.leuan.lepton.tenant.controller.vo

import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.tenant.dal.Tenant}
 */
data class TenantVO(
    var id: Long? = null,
    var name: String = ""
) : Serializable