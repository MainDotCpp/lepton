package com.leuan.lepton.framework.dict.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface DictRepository : JpaRepository<Dict, Long>, QuerydslPredicateExecutor<Dict> {}