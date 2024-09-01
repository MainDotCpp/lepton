package com.leuan.lepton.customer.brand.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.customer.brand.dal.Brand }
 */
@NoArgsConstructor
data class BrandVO(
    var id: Long, var name: String = ""
) : Serializable