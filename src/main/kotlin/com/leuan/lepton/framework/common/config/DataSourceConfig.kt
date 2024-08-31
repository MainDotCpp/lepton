package com.leuan.lepton.framework.common.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
class DataSourceConfig {

    @Primary
    @Bean("mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.main")
    fun mainDataSource(): DataSource{
        return DataSourceBuilder.create().build()
    }

    @Bean("collectorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.collector")
    fun collectorDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}