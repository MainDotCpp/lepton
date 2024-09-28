package com.leuan.lepton.form.component.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface ComponentRepository : JpaRepository<Component, Long>, QuerydslPredicateExecutor<Component> {}