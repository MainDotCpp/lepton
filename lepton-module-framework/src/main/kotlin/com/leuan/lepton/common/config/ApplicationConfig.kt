package com.leuan.lepton.common.config


import com.fasterxml.jackson.databind.ObjectMapper
import com.leuan.lepton.common.utils.objectMapper
import jakarta.annotation.PostConstruct
import jakarta.annotation.Resource
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties
class ApplicationConfig {


    @Bean
    fun objectMapper() = objectMapper

}