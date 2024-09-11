package com.leuan.lepton.framework.dept.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface DeptRepository : JpaRepository<Dept, Long>, QuerydslPredicateExecutor<Dept> {}