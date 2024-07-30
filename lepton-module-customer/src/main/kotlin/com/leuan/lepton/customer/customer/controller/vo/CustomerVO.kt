package com.leuan.lepton.customer.customer.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable
import java.util.*

@NoArgsConstructor
data class CustomerVO(
    var id: Long?,
    var name: String,
    var createdAt: Date,
    var updatedBy: Long?,
    var phone: String?,
    var wx: String?,
    var createdById: Long?,
    var createdByName: String?
) : Serializable