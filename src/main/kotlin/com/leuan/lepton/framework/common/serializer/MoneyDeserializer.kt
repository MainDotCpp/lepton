package com.leuan.lepton.framework.common.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.leuan.lepton.framework.common.log.logDebug
import java.math.BigDecimal

class MoneyDeserializer : JsonDeserializer<Long>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Long {
        logDebug("MoneyDeserializer.deserialize p: ${p.text}")
        val value = p.readValueAs(BigDecimal::class.java)
        return value.multiply(BigDecimal(100)).toLong()
    }

}