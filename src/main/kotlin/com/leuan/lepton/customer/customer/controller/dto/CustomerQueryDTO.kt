package com.leuan.lepton.customer.customer.controller.dto

import com.leuan.lepton.framework.common.dal.annotations.QueryField
import com.leuan.lepton.framework.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "租户查询参数")
class CustomerQueryDTO : BaseQueryDTO() {
    @QueryField(fieldName = "channel.id")
    @Schema(description = "渠道ID")
    var channelId: List<Long>? = null

    var keywords: String? = null
}