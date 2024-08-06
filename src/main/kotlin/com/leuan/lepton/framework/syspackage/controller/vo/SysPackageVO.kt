package com.leuan.lepton.framework.syspackage.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.syspackage.dal.SysPackage }
 */
@NoArgsConstructor
data class SysPackageVO(
    var id: Long,
    var name: String = "",
    var menuIds: MutableSet<Long>?
) : Serializable