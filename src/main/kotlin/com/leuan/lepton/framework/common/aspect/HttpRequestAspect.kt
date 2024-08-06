package com.leuan.lepton.framework.common.aspect

import com.leuan.lepton.framework.common.log.logInfo
import io.swagger.v3.oas.annotations.Operation
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class HttpRequestAspect {

    @Resource
    private lateinit var request: HttpServletRequest

    @Before("@annotation(io.swagger.v3.oas.annotations.Operation)")
    fun before(pj: ProceedingJoinPoint) {
        // 获取注解
        val signature = pj.signature as MethodSignature
        val operation = signature.method.getAnnotation(Operation::class.java)
        logInfo("[${operation.summary}] ${request.requestURI}")
    }

}