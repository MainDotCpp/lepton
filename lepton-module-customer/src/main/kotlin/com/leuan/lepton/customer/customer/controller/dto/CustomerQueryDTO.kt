package com.leuan.lepton.customer.customer.controller.dto

import com.leuan.lepton.common.http.PageParams
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "客资查询参数")
data class CustomerQueryDTO(
    @Schema(description = "客资ID")
    val id: Long? = null,
    @Schema(description = "当前页")
    override var current: Long = 1,
    @Schema(description = "每页大小")
    override var pageSize: Long = 10
) : PageParams