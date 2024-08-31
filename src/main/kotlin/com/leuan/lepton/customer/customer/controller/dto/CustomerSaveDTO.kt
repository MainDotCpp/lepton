package com.leuan.lepton.customer.customer.controller.dto

import lombok.NoArgsConstructor
import java.util.*

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer }
 */
@NoArgsConstructor
data class CustomerSaveDTO(
    val id: Long? = null,
    val createdAt: Date = Date(),
    val name: String = "",
    val phone: String? = null,
    val wechat: String? = null,
    val channelId: Long = 0,
    val photoType: String = "",
    val saleId: Long = 0,
    val source: String = "",
    val createdById: Long? = null,
    val followStatus: String? = null,
)
