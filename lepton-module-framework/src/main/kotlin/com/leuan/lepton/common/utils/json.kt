package com.leuan.lepton.common.utils

import com.fasterxml.jackson.databind.ObjectMapper

fun getObjectMapper(): ObjectMapper {
    val objectMapper = ObjectMapper()
    return objectMapper
}

fun <T : Any?> T.toJson(): String {
    val objectMapper = getObjectMapper()
    return objectMapper.writeValueAsString(this)
}
