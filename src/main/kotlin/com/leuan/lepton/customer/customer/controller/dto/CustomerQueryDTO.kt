package com.leuan.lepton.customer.customer.controller.dto

import com.leuan.lepton.framework.common.dal.annotations.QueryField
import com.leuan.lepton.framework.common.dal.annotations.QueryMethod
import com.leuan.lepton.framework.common.http.BaseQueryDTO
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

@NoArgsConstructor
@Schema(description = "租户查询参数")
class CustomerQueryDTO : BaseQueryDTO() {
    @QueryField(fieldName = "channel.id")
    @Schema(description = "渠道ID")
    var channelId: List<Long>? = null

    @QueryField(fieldName = "source")
    var source: List<String>? = null

    @QueryField(fieldName = "photoType")
    var photoType: List<String>? = null

    @QueryField(fieldName = "followStatus")
    var followStatus: List<String>? = null

    @QueryField(fieldName = "sale.id")
    var saleId: List<Long>? = null

    @QueryField(fieldName = "createdBy.id")
    var createdById: List<Long>? = null

    @QueryField(fieldName = "brand.id")
    var brandId: List<Long>? = null


    @QueryField(fieldName = "createdAt", method = QueryMethod.BETWEEN)
    var createdAt: List<String>? = null

    var keywords: String? = null


}