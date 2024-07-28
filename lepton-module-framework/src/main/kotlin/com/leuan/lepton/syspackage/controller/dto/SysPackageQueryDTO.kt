package com.leuan.lepton.syspackage.controller.dto

import com.leuan.lepton.common.annotations.NoArgs
import com.leuan.lepton.common.http.PageParams
import io.swagger.v3.oas.annotations.media.Schema

@NoArgs
@Schema(description = "系统套餐查询参数")
data class SysPackageQueryDTO(
    @Schema(description = "系统套餐ID")
    val id: Long? = null,
    @Schema(description = "当前页")
    override var current: Long = 1,
    @Schema(description = "每页大小")
    override var pageSize: Long = 10
) : PageParams