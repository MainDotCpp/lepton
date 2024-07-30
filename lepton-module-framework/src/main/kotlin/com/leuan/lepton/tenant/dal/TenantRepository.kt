package com.leuan.lepton.tenant.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface TenantRepository : JpaRepository<Tenant, Long>, QuerydslPredicateExecutor<Tenant> {}