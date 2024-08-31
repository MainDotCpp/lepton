package com.leuan.lepton.framework.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "collectorEntityManagerFactory",
    transactionManagerRef = "collectorTransactionManager",
    basePackages = ["com.leuan.lepton.collector"]
)
class CollectorDataSourceConfig {

    @Bean(name = ["collectorEntityManagerFactory"])
    fun entityManagerFactory(
        @Qualifier("collectorDataSource") dataSource: DataSource,
        collectorJpaProperties: JpaProperties,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder // 设置数据源
            .dataSource(dataSource)
            .properties(collectorJpaProperties.properties)
            .packages("com.leuan.lepton.collector") // 设置持久化单元名，用于@PersistenceContext注解获取EntityManager时指定数据源
            .persistenceUnit("collectorUnit").build()
    }

    @Bean(name = ["collectorEntityManager"])
    fun entityManager(@Qualifier("collectorEntityManagerFactory") entityManagerFactory: EntityManagerFactory): EntityManager {
        return entityManagerFactory.createEntityManager()
    }

    @Bean(name = ["collectorTransactionManager"])
    fun transactionManager(@Qualifier("collectorEntityManagerFactory") entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    @Bean(name = ["collectorJpaQueryFactory"])
    fun jpaQueryFactory(@Qualifier("collectorEntityManager") entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }

}