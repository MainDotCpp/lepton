package com.leuan.lepton.framework.user.controller.vo

import com.leuan.lepton.framework.user.enums.DataPermissionType
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.user.dal.User }
 */
@NoArgsConstructor
data class UserVO(
    var id: Long? = null,
    var name: String = "",
    var phone: String? = null,
    var avatar: String? = null,
    var roleIds: MutableSet<Long>?,
    var dataPermission: DataPermissionType?,
    var deptId: Long? = null,
) : Serializable {
}