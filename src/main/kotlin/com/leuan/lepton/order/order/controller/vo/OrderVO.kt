package com.leuan.lepton.order.order.controller.vo

import com.leuan.lepton.customer.customer.controller.vo.CustomerVO
import com.leuan.lepton.order.goods.controller.vo.GoodsVO
import lombok.NoArgsConstructor
import java.io.Serializable
import java.util.*

/**
 * DTO for {@link com.leuan.lepton.order.order.dal.Order }
 */
@NoArgsConstructor
data class OrderVO(
    var id: Long, var customer: CustomerVO? = null, var goods: GoodsVO, var sellerId: Long = 0,
    var createdAt: Date = Date(),
    var payAmount: Long = 0,
) : Serializable