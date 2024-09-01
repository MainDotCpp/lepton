package com.leuan.lepton.framework.config.dal

import jakarta.persistence.Embeddable
import lombok.Data

@Data
@Embeddable
class TenantConfig {
    var customerTarget: Int = 0
    var customerNotifyUrl: String? = null

    var orderTarget: Int = 0
    var orderNotifyUrl: String? = null
}