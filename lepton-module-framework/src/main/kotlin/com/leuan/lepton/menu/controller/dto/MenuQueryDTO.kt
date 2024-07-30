package com.leuan.lepton.menu.controller.dto

import com.leuan.lepton.common.http.PageParams
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "菜单查询参数")
data class MenuQueryDTO(
    @Schema(description = "菜单ID")
    val id: Long? = null,
    @Schema(description = "当前页")
    override var current: Long = 1,
    @Schema(description = "每页大小")
    override var pageSize: Long = 10
) : PageParams