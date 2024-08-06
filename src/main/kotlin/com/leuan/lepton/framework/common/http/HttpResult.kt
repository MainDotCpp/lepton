package com.leuan.lepton.framework.common.http

import com.leuan.lepton.framework.common.exception.BizErr

data class HttpResult<T : Any?>(val code: Int, val message: String, val data: T? = null)

fun <T> success(data: T? = null): HttpResult<T?> {
    return HttpResult(200, "success", data)
}

fun <T> fail(code: Int, message: String): HttpResult<T?> {
    return HttpResult(code, message)
}

fun <T> fail(bizErr: BizErr): HttpResult<T?> {
    return HttpResult(bizErr.code, bizErr.message)
}
