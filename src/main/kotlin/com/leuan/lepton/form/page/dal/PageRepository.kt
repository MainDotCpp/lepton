package com.leuan.lepton.form.page.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface PageRepository : JpaRepository<Page, Long>, QuerydslPredicateExecutor<Page> {}