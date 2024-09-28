package com.leuan.lepton.form.component.controller.dto

import com.leuan.lepton.framework.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "租户查询参数")
class  ComponentQueryDTO : BaseQueryDTO() {}