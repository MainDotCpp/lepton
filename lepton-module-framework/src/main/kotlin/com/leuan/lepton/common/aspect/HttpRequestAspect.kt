package com.leuan.lepton.common.aspect

import com.leuan.lepton.common.http.HttpResult
import com.leuan.lepton.common.http.success
import com.leuan.lepton.common.log.logInfo
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
@Slf4j
class HttpRequestAspect {


    @Pointcut("execution(* com.leuan.lepton.*.controller.*.*(..))")
    fun pointCut() {
    }

    /**
     * 自动包装返回值为HttpResult
     */

    @Around("pointCut()")
    fun around(pointcut: Pointcut): HttpResult<Any?>? {
        logInfo("HttpRequestAspect.around")
        return null
    }

}