package com.leuan.lepton.framework.common.config

import com.leuan.lepton.framework.common.dal.jpa
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig {
    @Bean
    fun jpaQueryFactory(entityManager: EntityManager?): JPAQueryFactory {
        jpa = JPAQueryFactory(entityManager)
        return jpa
    }
}
