package com.leuan.lepton.framework.common.utils

import com.leuan.lepton.framework.common.log.logInfo
import jakarta.annotation.Resource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.nio.charset.StandardCharsets

@Component
class ChatBotUtils {
    @Resource
    private lateinit var restTemplate: RestTemplate

    fun sendNotify(url: String?, message: String) {
        if (url == null) {
            logInfo("url未配置，取消发送")
            return
        }
        when {
            url.startsWith("https://qyapi.weixin.qq.com") -> sendNotifyByWeCom(url, message)
            url.startsWith("https://open.feishu.cn") -> sendNotifyByFeiShu(url, message)
            url.startsWith("https://oapi.dingtalk.com") -> sendNotifyByDingDing(url, message)
        }
    }

    /**
     * 由飞书 发送通知
     * @param [url] url
     * @param [message] 消息
     */
    private fun sendNotifyByFeiShu(url: String, message: String) {
        val data = mapOf(
            "msg_type" to "text",
            "content" to mapOf(
                "text" to message
            )
        )
        restTemplate.messageConverters
            .add(0, StringHttpMessageConverter(StandardCharsets.UTF_8))
        val res: String? = restTemplate.postForObject<String>(url, data, String::class.java)
        logInfo("发送企业微信通知结果 $res")
    }

    private fun sendNotifyByDingDing(url: String, message: String) {
        logInfo("发送钉钉通知")
        val httpHeaders = HttpHeaders()
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8")

        // 添加请求头

        // 添加请求头
        val body = """
                    {
                        "msgtype": "text",
                        "text": {
                          "content": "MESSAGE"
                        }
                    }
                    
                    """.trimIndent().replace("MESSAGE", "$message\n [Fision Admin]")
        val request = HttpEntity(body, httpHeaders)
        val res: String? = restTemplate.postForObject<String>(url, request, String::class.java)
    }

    /**
     * 通过企业微信发送通知
     * @param [url] url
     * @param [message] 消息
     */
    private fun sendNotifyByWeCom(url: String, message: String) {
        logInfo("发送企业微信通知")
        val body = """
                    {
                        "msgtype": "markdown",
                        "markdown": {
                          "content": "MESSAGE"
                        }
                    }
                    
                    """.trimIndent().replace("MESSAGE", message)
        restTemplate.messageConverters
            .add(0, StringHttpMessageConverter(StandardCharsets.UTF_8))
        val res: String? = restTemplate.postForObject<String>(url, body, String::class.java)
        logInfo("发送企业微信通知结果 $res")
    }

}