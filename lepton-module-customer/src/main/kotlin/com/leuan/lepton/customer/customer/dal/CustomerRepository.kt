package com.leuan.lepton.customer.customer.dal;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface CustomerRepository : JpaRepositoryImplementation<Customer, Long>, QuerydslPredicateExecutor<Customer> {


}