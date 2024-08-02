package com.leuan.lepton.role.controller.dto

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.role.dal.Role }
 */
@NoArgsConstructor
data class RoleSaveDTO(
    val id: Long? = null, val name: String = "", val code: String = "", val menus: MutableSet<MenuDto> = mutableSetOf()
) {
    /**
     * DTO for {@link com.leuan.lepton.menu.dal.Menu}
     */
    data class MenuDto(val id: Long? = null) : Serializable
}
