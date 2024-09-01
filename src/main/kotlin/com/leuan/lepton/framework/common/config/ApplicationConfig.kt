package com.leuan.lepton.framework.common.config


import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.client.RestTemplate

@Configuration
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableConfigurationProperties
class ApplicationConfig {
    @Bean
    fun restTemplate() = RestTemplate()
}