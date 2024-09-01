package com.leuan.lepton.customer.brand.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface BrandRepository : JpaRepository<Brand, Long>, QuerydslPredicateExecutor<Brand> {}