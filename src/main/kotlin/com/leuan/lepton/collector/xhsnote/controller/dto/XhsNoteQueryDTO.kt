package com.leuan.lepton.collector.xhsnote.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.leuan.lepton.framework.common.dal.annotations.QueryField
import com.leuan.lepton.framework.common.dal.annotations.QueryMethod
import com.leuan.lepton.framework.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "租户查询参数")
class  XhsNoteQueryDTO : BaseQueryDTO() {}