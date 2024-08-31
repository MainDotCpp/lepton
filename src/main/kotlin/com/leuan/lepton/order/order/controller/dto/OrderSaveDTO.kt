package com.leuan.lepton.order.order.controller.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.leuan.lepton.framework.common.serializer.MoneyDeserializer
import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.order.order.dal.Order }
 */
@NoArgsConstructor
class OrderSaveDTO {
    val id: Long? = null
    val customerId: Long = 0
    val goodsId: Long = 0
    @JsonDeserialize(using = MoneyDeserializer::class)
    val payAmount: Long = 0
    val sellerId: Long = 0
}
