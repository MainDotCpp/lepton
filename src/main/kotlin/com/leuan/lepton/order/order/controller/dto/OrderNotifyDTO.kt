package com.leuan.lepton.order.order.controller.dto

import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.order.order.dal.Order}
 */
data class OrderNotifyDTO(
    var id: Long = 0,
    var customerName: String = "",
    var customerPhone: String? = null,
    var customerWechat: String? = null,
    var customerChannelName: String = "",
    var customerPhotoType: String = "",
    var customerSource: String = "",
    var goodsName: String = "",
    var goodsPrice: Long = 0,
    var payAmount: Long = 0,
    var sellerName: String = ""
) : Serializable