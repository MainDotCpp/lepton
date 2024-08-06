package com.leuan.lepton.customer.channel.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface ChannelRepository : JpaRepository<Channel, Long>, QuerydslPredicateExecutor<Channel> {}