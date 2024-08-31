package com.leuan.lepton.framework.common.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize

class MoneySerializer : JsonSerializer<Long>() {
    override fun serialize(value: Long, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString((value / 100).toString())
    }
}