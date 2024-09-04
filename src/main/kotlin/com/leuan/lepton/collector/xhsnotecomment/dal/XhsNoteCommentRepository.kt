package com.leuan.lepton.collector.xhsnotecomment.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface XhsNoteCommentRepository : JpaRepository<XhsNoteComment, Long>, QuerydslPredicateExecutor<XhsNoteComment> {}