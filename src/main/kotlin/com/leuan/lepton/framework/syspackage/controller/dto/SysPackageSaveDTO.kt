package com.leuan.lepton.framework.syspackage.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.framework.syspackage.dal.SysPackage }
 */
@NoArgsConstructor
class SysPackageSaveDTO(
    val id: Long? = null,
    val name: String = "",
    var menuIds: MutableSet<Long>?,
)
