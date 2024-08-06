package com.leuan.lepton.framework.common.aspect

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.HttpResult
import com.leuan.lepton.framework.common.http.fail
import com.leuan.lepton.framework.common.log.logError
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [BizErr::class])
    fun handleBizErr(e: BizErr): HttpResult<Any?> {
        logError(e)
        return fail(e.code, e.message)
    }

    @ExceptionHandler(value = [NoResourceFoundException::class])
    fun handleNoResourceFoundException(e: NoResourceFoundException): HttpResult<Any?> {
        logError(e)
        return fail(BizErr(BizErrEnum.RESOURCE_NOT_FOUND))
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(e: IllegalArgumentException): HttpResult<Any?> {
        logError(e)
        return fail(BizErr(BizErrEnum.INVALID_PARAM))
    }

    @ExceptionHandler(value = [AuthorizationDeniedException::class])
    fun handleAuthorizationDeniedException(e: AuthorizationDeniedException): HttpResult<Any?> {
        logError(e)
        return fail(BizErr(BizErrEnum.ACCESS_DENIED))
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): HttpResult<Any?> {
        logError(e)
        return fail(BizErr(BizErrEnum.BIZ))
    }

}