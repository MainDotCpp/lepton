package com.leuan.lepton.framework.common.utils

import com.leuan.lepton.framework.common.log.logInfo
import org.redisson.api.RedissonClient
import java.time.Duration

object CacheUtils

lateinit var redissonClient: RedissonClient

inline fun <R : Any, reified T> R.cache(key: String, ttl: Long? = null, fresh: Boolean = false, process: () -> T): T {
    val bucket = redissonClient.getBucket<String>(key)
    val value = bucket.get()
    if (value != null && !fresh) {
        return value.fromJson<T>().also {
            logInfo("[Cache] GET $key -> $it")
        }
    }
    val result = process()
    result.toJson().also {
        bucket.set(it)
        ttl?.let { bucket.expire(Duration.ofSeconds(ttl)) }
        logInfo("[Cache] SET $key -> $it")
    }
    return result
}

