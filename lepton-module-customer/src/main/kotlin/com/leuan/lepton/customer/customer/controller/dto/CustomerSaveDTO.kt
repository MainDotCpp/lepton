package com.leuan.lepton.customer.customer.controller.dto

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer }
 */
data class CustomerSaveDTO(
    val id: Long? = null, val name: String? = null, val phone: String? = null, val wx: String? = null,
)
