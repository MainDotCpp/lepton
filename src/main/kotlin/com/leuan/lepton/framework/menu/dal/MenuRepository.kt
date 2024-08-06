package com.leuan.lepton.framework.menu.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface MenuRepository : JpaRepository<Menu, Long>, QuerydslPredicateExecutor<Menu> {}