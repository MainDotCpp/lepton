package com.leuan.lepton.framework.config.dal

import jakarta.persistence.Embeddable
import lombok.Data

@Data
@Embeddable
class TenantConfig {

    var customerNotifyUrl: String? = null
    var orderNotifyUrl:String? = null
}