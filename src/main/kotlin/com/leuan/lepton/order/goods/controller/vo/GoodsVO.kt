package com.leuan.lepton.order.goods.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.order.goods.dal.Goods }
 */
@NoArgsConstructor
data class GoodsVO(
    var id: Long, var name: String = "", var price: Long = 0, var status: Int = 1
) : Serializable