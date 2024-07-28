package com.leuan.lepton.common.utils

import cn.hutool.extra.spring.SpringUtil
import com.fasterxml.jackson.core.type.TypeReference
import com.leuan.lepton.common.log.logInfo
import org.redisson.api.RedissonClient
import org.redisson.codec.TypedJsonJacksonCodec

object CacheUtils

lateinit var redissonClient: RedissonClient

inline fun <reified T> cache(key: String, process: () -> T): T {
    val bucket = redissonClient.getBucket<String>(key)
    val value = bucket.get()
    if (value != null) {
        return value.fromJson<T>().also {
            CacheUtils.logInfo("[Cache] GET $key -> $it")
        }
    }
    val result = process()
    result.toJson().also {
        bucket.set(it)
        CacheUtils.logInfo("[Cache] SET $key -> $it")
    }
    return result
}
