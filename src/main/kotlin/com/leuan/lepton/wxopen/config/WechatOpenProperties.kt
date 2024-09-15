package com.leuan.lepton.wxopen.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "wechat.wxopen")
class WechatOpenProperties {
    /**
     * 设置微信三方平台的appid
     */
    var componentAppId: String? = null

    /**
     * 设置微信三方平台的app secret
     */
    var componentSecret: String? = null

    /**
     * 设置微信三方平台的token
     */
    var componentToken: String? = null

    /**
     * 设置微信三方平台的EncodingAESKey
     */
    var componentAesKey: String? = null
}