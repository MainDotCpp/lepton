package com.leuan.lepton.customer.brand.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.customer.brand.dal.Brand }
 */
@NoArgsConstructor
data class BrandSaveDTO(
    val id: Long? = null, val name: String = ""
)
