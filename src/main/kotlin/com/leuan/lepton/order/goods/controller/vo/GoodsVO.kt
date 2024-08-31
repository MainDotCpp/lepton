package com.leuan.lepton.order.goods.controller.vo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.leuan.lepton.framework.common.serializer.MoneyDeserializer
import com.leuan.lepton.framework.common.serializer.MoneySerializer
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.order.goods.dal.Goods }
 */
@NoArgsConstructor
class GoodsVO(
) : Serializable{

    var id: Long = 0
    var name: String = ""
    @JsonSerialize(using = MoneySerializer::class)
    var price: Long = 0
    var status: Int = 1
}