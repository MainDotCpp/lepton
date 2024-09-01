package com.leuan.lepton.framework.config.dal

import jakarta.persistence.Embeddable
import lombok.Data

@Data
@Embeddable
class GlobalConfig {
    var notifyTemplate: String = ""
}