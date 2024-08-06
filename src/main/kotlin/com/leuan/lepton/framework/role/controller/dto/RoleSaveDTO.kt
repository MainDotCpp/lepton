package com.leuan.lepton.framework.role.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.framework.role.dal.Role }
 */
@NoArgsConstructor
data class RoleSaveDTO(
    val id: Long? = null, val name: String = "", val code: String = "", var menuIds: MutableSet<Long>?
) {

}
