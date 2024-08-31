package com.leuan.lepton.collector.xhsnote.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

interface XhsNoteRepository : JpaRepository<XhsNote, Long>, QuerydslPredicateExecutor<XhsNote> {}