package com.leuan.lepton.common.config

import com.leuan.lepton.common.dal.jpa
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
