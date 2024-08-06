package com.leuan.lepton.framework.common.config

import jakarta.annotation.Resource
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.client.codec.StringCodec
import org.redisson.config.Config
import org.redisson.config.SingleServerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment


@Configuration
class RedissonConfig {

    @Resource
    private lateinit var environment: Environment

    @Bean
    fun redissonClient(): RedissonClient? {
        val conf = Config()
        conf.setCodec(StringCodec())

        //单节点模式
        val singleServerConfig: SingleServerConfig = conf.useSingleServer()
        singleServerConfig.setAddress(environment.getProperty("spring.data.redis.address"))
        singleServerConfig.setDatabase(environment.getProperty("spring.data.redis.database", Int::class.java, 0))
        singleServerConfig.setPassword(environment.getProperty("spring.data.redis.password"))

        com.leuan.lepton.framework.common.utils.redissonClient = Redisson.create(conf)
        return com.leuan.lepton.framework.common.utils.redissonClient
    }
}