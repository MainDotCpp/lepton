package com.leuan.lepton.framework.tenant.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.leuan.lepton.framework.common.dal.annotations.QueryField
import com.leuan.lepton.framework.common.dal.annotations.QueryMethod
import com.leuan.lepton.framework.common.http.BaseQueryDTO
import com.leuan.lepton.framework.tenant.enums.TenantTypeEnum
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "租户查询参数")
class TenantQueryDTO : BaseQueryDTO() {
    @JsonProperty
    @QueryField("name", QueryMethod.BETWEEN)
    var id: List<Long>? = null
    var logo: List<String>? = null
    var name: List<String>? = null
    var type: List<TenantTypeEnum>? = null
    var code: List<String>? = null
}