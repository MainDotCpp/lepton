package com.leuan.lepton.common.aspect

import com.leuan.lepton.common.http.HttpResult
import com.leuan.lepton.common.log.logInfo
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class HttpRequestAspect {


    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    fun pointCut() {
    }

    @Around("pointCut()")
    fun around(pj: ProceedingJoinPoint): Any? {
        logInfo("HttpRequestAspect.around")
        val result = pj.proceed()
        return result
    }

}