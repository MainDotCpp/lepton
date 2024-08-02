package com.leuan.lepton.menu.controller.dto

import com.leuan.lepton.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "菜单查询参数")
class MenuQueryDTO(
    @Schema(description = "菜单ID")
    val id: Long? = null,
) : BaseQueryDTO()