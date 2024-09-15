package com.leuan.lepton.wxopen.controller

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.log.logDebug
import com.leuan.lepton.framework.common.log.logError
import com.leuan.lepton.framework.common.log.logInfo
import jakarta.annotation.Resource
import me.chanjar.weixin.common.error.WxErrorException
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage
import me.chanjar.weixin.open.api.WxOpenService
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage
import me.chanjar.weixin.open.util.WxOpenCryptUtil
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("wxopen")
class WxOpenMessageController {

    @Resource
    private lateinit var wxOpenService:  WxOpenService

    @RequestMapping("receiveTicket")
    fun receiveTicket(
        @RequestBody(required = false) requestBody: String?, @RequestParam("timestamp") timestamp: String?,
        @RequestParam("nonce") nonce: String?, @RequestParam("signature") signature: String?,
        @RequestParam(name = "encrypt_type", required = false) encType: String?,
        @RequestParam(name = "msg_signature", required = false) msgSignature: String?
    ): Any? {
        logInfo(
            "\n接收微信请求：[signature=[$signature], encType=[$encType], msgSignature=[$msgSignature] timestamp=[$timestamp], nonce=[$nonce], requestBody=[\n$requestBody\n] "
        )

        if (!StringUtils.equalsIgnoreCase("aes", encType) || !wxOpenService.wxOpenComponentService
                .checkSignature(timestamp, nonce, signature)
        ) {
            throw IllegalArgumentException("非法请求，可能属于伪造的请求！")
        }

        // aes加密的消息
        val inMessage = WxOpenXmlMessage.fromEncryptedXml(
            requestBody,
            wxOpenService.wxOpenConfigStorage,
            timestamp,
            nonce,
            msgSignature
        )
        logDebug("\n消息解密后内容为：\n${inMessage} " )
        var out: String? = null
        try {
            out = wxOpenService.wxOpenComponentService.route(inMessage)
        } catch (e: WxErrorException) {
            throw BizErr(BizErrEnum.BIZ)
        }

        logDebug("\n组装回复信息：${out}")

        return out
    }

    @RequestMapping("{appId}/callback")
    fun callback(
        @RequestBody(required = false) requestBody: String,
        @PathVariable("appId") appId: String?,
        @RequestParam("signature") signature: String?,
        @RequestParam("timestamp") timestamp: String?,
        @RequestParam("nonce") nonce: String?,
        @RequestParam("openid") openid: String?,
        @RequestParam("encrypt_type") encType: String?,
        @RequestParam("msg_signature") msgSignature: String?
    ): Any {
        logInfo(
            ("\n接收微信请求：[appId=[$appId], openid=[$openid], signature=[$signature], encType=[$encType], msgSignature=[$msgSignature],"
                    + " timestamp=[$timestamp], nonce=[$nonce], requestBody=[\n$requestBody\n] ")
        )
        if (!encType!!.startsWith("aes",true) || wxOpenService.wxOpenComponentService.checkSignature(timestamp, nonce, signature)) {
            throw IllegalArgumentException("非法请求，可能属于伪造的请求！")
        }

        var out = ""
        // aes加密的消息
        val inMessage = WxOpenXmlMessage.fromEncryptedMpXml(
            requestBody,
            wxOpenService.wxOpenConfigStorage,
            timestamp,
            nonce,
            msgSignature
        )
        logDebug("\n消息解密后内容为：\n$inMessage ")
        // 全网发布测试用例
        if (StringUtils.equalsAnyIgnoreCase(appId, "wxd101a85aa106f53e", "wx570bc396a51b8ff8")) {
            try {
                if (StringUtils.equals(inMessage.msgType, "text")) {
                    if (StringUtils.equals(inMessage.content, "TESTCOMPONENT_MSG_TYPE_TEXT")) {
                        out = WxOpenCryptUtil(wxOpenService.wxOpenConfigStorage).encrypt(
                            WxMpXmlOutMessage.TEXT().content("TESTCOMPONENT_MSG_TYPE_TEXT_callback")
                                .fromUser(inMessage.toUser)
                                .toUser(inMessage.fromUser)
                                .build()
                                .toXml()
                        )
                    } else if (StringUtils.startsWith(inMessage.content, "QUERY_AUTH_CODE:")) {
                        val msg = inMessage.content.replace("QUERY_AUTH_CODE:", "") + "_from_api"
                        val kefuMessage = WxMpKefuMessage.TEXT().content(msg).toUser(inMessage.fromUser).build()
                        wxOpenService.wxOpenComponentService.getWxMpServiceByAppid(appId).kefuService
                            .sendKefuMessage(kefuMessage)
                    }
                } else if (StringUtils.equals(inMessage.msgType, "event")) {
                    val kefuMessage =
                        WxMpKefuMessage.TEXT().content(inMessage.event + "from_callback").toUser(inMessage.fromUser)
                            .build()
                    wxOpenService.wxOpenComponentService.getWxMpServiceByAppid(appId).kefuService
                        .sendKefuMessage(kefuMessage)
                }
            } catch (e: WxErrorException) {
                logError(e)
            }
        }
        return out
    }

}