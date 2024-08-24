package com.leuan.lepton.framework.common.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
class DataSourceConfig {

    @Bean(name = ["mainDataSource"])
    @Qualifier("mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    fun primaryDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["collectorDataSource"])
    @Qualifier("collectorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    fun secondDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}