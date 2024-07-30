package com.leuan.lepton.syspackage.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.syspackage.dal.SysPackage }
 */
@NoArgsConstructor
data class SysPackageSaveDTO(
    val id: Long? = null,
    val name: String = "",
    val menuIds: MutableSet<Long>?,
)
