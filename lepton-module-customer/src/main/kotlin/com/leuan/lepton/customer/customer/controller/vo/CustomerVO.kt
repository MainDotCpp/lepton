package com.leuan.lepton.customer.customer.controller.vo

import java.io.Serializable
import java.util.*

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer }
 */
data class CustomerVO(
    var id: Long? = null,
    var name: String = "", var createdAt: Date = Date(), var updatedBy: Long = 0, var phone: String? = null, var wx: String? = null
) : Serializable