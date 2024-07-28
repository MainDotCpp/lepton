package com.leuan.lepton.syspackage.controller.vo

import com.leuan.lepton.common.annotations.NoArgs
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.syspackage.dal.SysPackage }
 */
@NoArgs
data class SysPackageVO(
    var id: Long, var name: String = ""
) : Serializable