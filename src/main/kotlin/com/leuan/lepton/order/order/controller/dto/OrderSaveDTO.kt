package com.leuan.lepton.order.order.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.order.order.dal.Order }
 */
@NoArgsConstructor
data class OrderSaveDTO(
    val id: Long? = null, val customerId: Long = 0, val goodsId: Long = 0, val payAmount: Long = 0, val sellerId: Long = 0
)
