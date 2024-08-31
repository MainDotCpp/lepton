package com.leuan.lepton.framework.common.config

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
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

    @Bean
    @Primary
    fun entityManagerFactory(
        mainDataSource: DataSource,
        mainJpaProperties: JpaProperties,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder // 设置数据源
            .dataSource(mainDataSource) // 设置jpa配置
            .properties(mainJpaProperties.properties) // 设置实体包名
            .packages(
                "com.leuan.lepton.framework",
                "com.leuan.lepton.customer"
            ) // 设置持久化单元名，用于@PersistenceContext注解获取EntityManager时指定数据源
            .persistenceUnit("mainUnit").build()
    }

    @Primary
    @Bean
    fun entityManager(entityManagerFactory: EntityManagerFactory): EntityManager {
        return entityManagerFactory.createEntityManager()
    }

    @Primary
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory!!)
    }
}