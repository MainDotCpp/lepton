package com.leuan.lepton.framework.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@ConfigurationProperties(prefix = "lepton.auth")
@Component
data class AuthConfig(
    var tokenExpireDays: Long = 7,
)

@ConfigurationProperties(prefix = "lepton")
@Component
data class LeptonConfig(
    var ignoreTenantUrl: List<String> = emptyList(),
    var ignoreTenantTable: List<String> = emptyList(),
    var ignoreLoginUrl: List<String> = emptyList(),
    var auth: AuthConfig = AuthConfig()
)