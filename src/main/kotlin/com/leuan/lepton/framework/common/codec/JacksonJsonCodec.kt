package com.leuan.lepton.framework.common.codec

import com.leuan.lepton.framework.common.utils.objectMapper
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.ByteBufInputStream
import org.redisson.client.codec.BaseCodec
import org.redisson.client.protocol.Decoder
import org.redisson.client.protocol.Encoder
import java.io.InputStream

class JacksonJsonCodec : BaseCodec() {
    override fun getValueDecoder(): Decoder<Any> {
        return Decoder { buf, state ->
            val inputStream = ByteBufInputStream(buf) as InputStream
            objectMapper.readValue(inputStream, Any::class.java)
        }
    }

    override fun getValueEncoder(): Encoder = Encoder { value ->
        val buffer = ByteBufAllocator.DEFAULT.buffer()
        buffer.writeBytes(objectMapper.writeValueAsBytes(value))
        buffer
    }
}