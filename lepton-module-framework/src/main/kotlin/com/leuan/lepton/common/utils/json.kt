package com.leuan.lepton.common.utils

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

var objectMapper = ObjectMapper().also {
    it.findAndRegisterModules()
    it.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    it.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
}

fun <T : Any?> T.toJson(): String {
    return objectMapper.writeValueAsString(this)
}

inline fun <reified T> String.fromJson(): T {
    return objectMapper.readValue(this, T::class.java)
}
