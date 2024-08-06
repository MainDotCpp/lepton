package com.leuan.lepton.customer.customer.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer }
 */
@NoArgsConstructor
data class CustomerSaveDTO(
    val id: Long? = null
)
