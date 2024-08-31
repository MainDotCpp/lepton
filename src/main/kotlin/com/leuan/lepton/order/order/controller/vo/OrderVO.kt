package com.leuan.lepton.order.order.controller.vo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.framework.common.serializer.MoneyDeserializer
import com.leuan.lepton.framework.common.serializer.MoneySerializer
import com.leuan.lepton.order.goods.controller.vo.GoodsVO
import lombok.NoArgsConstructor
import java.io.Serializable
import java.util.*

/**
 * DTO for {@link com.leuan.lepton.order.order.dal.Order }
 */
@NoArgsConstructor
class OrderVO(
) : Serializable {
    var id: Long? = null
    var customer: CustomerVO? = null
    var goods: GoodsVO = GoodsVO()
    var sellerId: Long = 0
    var createdAt: Date = Date()

    @JsonSerialize(using = MoneySerializer::class)
    var payAmount: Long = 0
}