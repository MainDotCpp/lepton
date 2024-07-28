package com.leuan.lepton.common.utils

import com.fasterxml.jackson.databind.ObjectMapper

var objectMapper = ObjectMapper()
fun <T : Any?> T.toJson(): String {
    return objectMapper.writeValueAsString(this)
}

inline fun <reified T> String.fromJson(): T {
    return objectMapper.readValue(this, T::class.java)
}
