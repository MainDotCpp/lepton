package com.leuan.lepton.framework.config.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface ConfigRepository : JpaRepository<Config, Long>, QuerydslPredicateExecutor<Config> {}