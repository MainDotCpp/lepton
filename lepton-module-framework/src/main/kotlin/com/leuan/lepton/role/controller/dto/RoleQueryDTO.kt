package com.leuan.lepton.role.controller.dto

import com.leuan.lepton.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "角色查询参数")
class RoleQueryDTO() : BaseQueryDTO()