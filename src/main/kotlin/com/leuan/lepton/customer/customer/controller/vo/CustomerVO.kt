package com.leuan.lepton.customer.customer.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer }
 */
@NoArgsConstructor
data class CustomerVO(
    var id: Long
) : Serializable