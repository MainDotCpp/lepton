package com.leuan.lepton.role.controller.vo

import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.role.dal.Role }
 */
data class `RoleVO`(
    var id: Long? = null,
    var name: String = ""
) : Serializable