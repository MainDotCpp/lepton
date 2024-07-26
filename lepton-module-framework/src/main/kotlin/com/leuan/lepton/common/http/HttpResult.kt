package com.leuan.lepton.common.http

data class HttpResult<T : Any?>(val code: Int, val message: String, val data: T? = null)

fun <T> success(data: T? = null): HttpResult<T?> {
    return HttpResult(200, "success", data)
}

fun <T> fail(code: Int, message: String): HttpResult<T?> {
    return HttpResult(code, message)
}
