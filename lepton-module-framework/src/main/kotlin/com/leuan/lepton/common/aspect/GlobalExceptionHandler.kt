package com.leuan.lepton.common.aspect

import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.HttpResult
import com.leuan.lepton.common.http.fail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [BizErr::class])
    fun handleBizErr(e: BizErr): HttpResult<Any?> {
        e.printStackTrace()
        return fail(e.code, e.message)
    }

    @ExceptionHandler(value = [NoResourceFoundException::class])
    fun handleNoResourceFoundException(e: NoResourceFoundException): HttpResult<Any?> {
        e.printStackTrace()
        return fail(404, e.message ?: "未找到资源")
    }
    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): HttpResult<Any?> {
        e.printStackTrace()
        return fail(500, e.message ?: "未知错误")
    }

}