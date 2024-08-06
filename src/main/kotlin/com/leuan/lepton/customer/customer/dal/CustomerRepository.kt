package com.leuan.lepton.customer.customer.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface CustomerRepository : JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {}