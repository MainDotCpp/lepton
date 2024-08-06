package com.leuan.lepton.framework.syspackage.dal;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface SysPackageRepository : JpaRepository<SysPackage, Long>, QuerydslPredicateExecutor<SysPackage> {}