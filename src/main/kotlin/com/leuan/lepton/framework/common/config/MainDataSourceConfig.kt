package com.leuan.lepton.framework.common.config

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager",
    basePackages = ["com.leuan.lepton.framework", "com.leuan.lepton.customer"]
)
class MainDataSourceConfig {

    @Primary
    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
        collectorJpaProperties: JpaProperties,
        builder: EntityManagerFactoryBuilder,
        hibernateProperties: HibernateProperties
    ): LocalContainerEntityManagerFactoryBean {
        return builder // 设置数据源
            .dataSource(dataSource)
            .properties(
                hibernateProperties.determineHibernateProperties(
                    collectorJpaProperties.properties,
                    HibernateSettings()
                )
            )
            .packages(
                "com.leuan.lepton.framework",
                "com.leuan.lepton.customer"
            )
            .persistenceUnit("mainUnit").build()
    }

    @Primary
    @Bean
    fun entityManager(entityManagerFactory: EntityManagerFactory): EntityManager {
        return entityManagerFactory.createEntityManager()
    }

    @Primary
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

}