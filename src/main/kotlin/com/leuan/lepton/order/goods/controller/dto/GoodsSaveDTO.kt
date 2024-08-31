package com.leuan.lepton.order.goods.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.order.goods.dal.Goods }
 */
@NoArgsConstructor
data class GoodsSaveDTO(
    val id: Long? = null, val name: String = "", val price: Long = 0, val status: Int = 1
)
