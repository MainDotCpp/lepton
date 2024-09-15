package com.leuan.lepton.wxopen.service

import com.leuan.lepton.wxopen.config.WechatOpenProperties
import jakarta.annotation.PostConstruct
import jakarta.annotation.Resource
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult
import me.chanjar.weixin.open.api.WxOpenComponentService
import me.chanjar.weixin.open.api.WxOpenConfigStorage
import me.chanjar.weixin.open.api.WxOpenService
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage
import me.chanjar.weixin.open.api.impl.WxOpenInRedissonConfigStorage
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl
import org.redisson.api.RedissonClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.io.File

@Service
@EnableConfigurationProperties(value = [WechatOpenProperties::class])
class WxOpenService : WxOpenServiceImpl() {

    @Resource
    private lateinit var redissonClient: RedissonClient
    @Resource
    private lateinit var wechatOpenProperties: WechatOpenProperties

    @PostConstruct
    fun initWxOpenService() {
        val inRedisConfigStorage = WxOpenInRedissonConfigStorage(redissonClient)
        inRedisConfigStorage.componentAppId = wechatOpenProperties.componentAppId
        inRedisConfigStorage.componentAppSecret = wechatOpenProperties.componentSecret
        inRedisConfigStorage.componentToken = wechatOpenProperties.componentToken
        inRedisConfigStorage.componentAesKey = wechatOpenProperties.componentAesKey
        this.wxOpenConfigStorage = inRedisConfigStorage
    }

}