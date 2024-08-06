package com.leuan.lepton.framework.role.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable
import java.util.*

/**
 * DTO for {@link com.leuan.lepton.framework.role.dal.Role }
 */
@NoArgsConstructor
class RoleVO(
    var id: Long, var menuIds: MutableSet<Long>?
) : Serializable {
    var createdByName: String? = ""
    var createdAt: Date? = Date()
    var name: String? = ""
    var code: String? = ""
    var builtin: Boolean? = false

}