package com.leuan.lepton.framework.common.config

import com.leuan.lepton.framework.common.dal.jpa
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig {

    @PersistenceContext
    private lateinit var mainEntityManager: EntityManager

    @PersistenceContext
    private lateinit var collectorEntityManager: EntityManager

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        jpa = JPAQueryFactory(mainEntityManager)
        return jpa
    }



}
