package com.leuan.lepton.framework.user.controller.dto

import com.leuan.lepton.framework.user.enums.DataPermissionType
import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.framework.user.dal.User }
 */
@NoArgsConstructor
data class UserSaveDTO(
    val id: Long? = null,
    val name: String? = null,
    val phone: String? = null,
    var password: String? = null,
    val avatar: String = "",
    val roleIds: MutableSet<Long> = mutableSetOf(),
    val dataPermission: DataPermissionType = DataPermissionType.SELF,
    val deptId: Long? = null,
)
