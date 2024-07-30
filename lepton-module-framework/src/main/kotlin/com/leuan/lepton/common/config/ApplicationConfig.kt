package com.leuan.lepton.common.config


import com.leuan.lepton.common.utils.objectMapper
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableConfigurationProperties
class ApplicationConfig {

    @Bean
    fun objectMapper() = objectMapper

}