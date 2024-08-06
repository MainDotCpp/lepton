package com.leuan.lepton.framework.syspackage.controller.dto

import com.leuan.lepton.framework.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "系统套餐查询参数")
class SysPackageQueryDTO() : BaseQueryDTO()