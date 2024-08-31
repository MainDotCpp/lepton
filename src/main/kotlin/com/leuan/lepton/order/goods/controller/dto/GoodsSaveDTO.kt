package com.leuan.lepton.order.goods.controller.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.leuan.lepton.framework.common.serializer.MoneyDeserializer
import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.order.goods.dal.Goods }
 */
@NoArgsConstructor
class GoodsSaveDTO(
) {
    val id: Long? = null
    val name: String = ""
    @JsonDeserialize(using = MoneyDeserializer::class)
    val price: Long = 0
    val status: Int = 1
}
