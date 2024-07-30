package com.leuan.lepton.user.controller.vo

import com.leuan.lepton.tenant.dal.Tenant
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.user.dal.User }
 */
@NoArgsConstructor
data class UserVO(
    var id: Long? = null,
    var name: String = "",
    var phone: String? = null,
    var tenants: MutableSet<Tenant> = mutableSetOf()
) : Serializable