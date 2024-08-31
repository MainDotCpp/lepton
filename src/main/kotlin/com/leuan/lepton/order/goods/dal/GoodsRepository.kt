package com.leuan.lepton.order.goods.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface GoodsRepository : JpaRepository<Goods, Long>, QuerydslPredicateExecutor<Goods> {}