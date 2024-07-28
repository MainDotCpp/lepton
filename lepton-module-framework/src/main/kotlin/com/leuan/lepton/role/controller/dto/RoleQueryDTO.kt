package com.leuan.lepton.role.controller.dto

import com.leuan.lepton.common.http.PageParams
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "系统角色查询参数")
data class RoleQueryDTO(
    @Schema(description = "系统角色ID")
    val id: Long? = null,
    @Schema(description = "当前页")
    override var current: Long = 1,
    @Schema(description = "每页大小")
    override var pageSize: Long = 10
) : PageParams