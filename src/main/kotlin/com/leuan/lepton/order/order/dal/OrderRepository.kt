package com.leuan.lepton.order.order.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface OrderRepository : JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {}