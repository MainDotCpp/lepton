package com.leuan.lepton.customer.customer.bo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.customer.customer.dal.Customer}
 */
@NoArgsConstructor
data class CustomerNotifyBO(
    var id: Long = 0,
    var createdByName: String? = null,
    var saleName:String? = null,
    var name: String = "",
    var phone: String? = null,
    var wechat: String? = null,
    var photoType: String = "",
    var source: String = "",
    var followStatus: String? = null,
    var remark: String? = null,
    var count: Int = 5,
    var brandName: String = "",
) : Serializable
