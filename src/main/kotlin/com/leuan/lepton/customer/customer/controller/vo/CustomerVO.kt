package com.leuan.lepton.customer.customer.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer }
 */
@NoArgsConstructor
data class CustomerVO(
    var id: Long,
    var name: String = "",
    var phone: String? = null,
    var wechat: String? = null,
    var channelId: Long = 0,
    var photoType: String = "",
    var saleId: Long = 0, var source: String = ""
) : Serializable