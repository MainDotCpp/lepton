package com.leuan.lepton.framework.common.aspect

import com.leuan.lepton.framework.common.http.HttpResult
import com.leuan.lepton.framework.common.http.success
import com.leuan.lepton.framework.common.utils.toJson
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class RestResponseAdvice : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        // 指定返回的结果为application/json格式
        // 不指定，String类型转json后返回Content-Type是text/plain;charset=UTF-8
        response.headers.contentType = MediaType.APPLICATION_JSON
        val result = success(data = body)

        // 若返回类型是ResultJson，则不进行修改
        if (body == null) {
            if (returnType.parameterType.isAssignableFrom(String::class.java)) {
                return result.toJson()
            }
        } else if (body is HttpResult<*>) {
            return body
        } else if (body is String) {
            return body
        } else if (request.uri.path == "/v3/api-docs") {
            return body
        }
        return result
    }
}