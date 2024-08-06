package com.leuan.lepton.framework.role.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface RoleRepository : JpaRepository<Role, Long>, QuerydslPredicateExecutor<Role> {}