package com.leuan.lepton.framework.auth.service

import cn.hutool.jwt.JWTUtil
import com.leuan.lepton.framework.auth.controller.dto.LoginDTO
import com.leuan.lepton.framework.common.config.LeptonConfig
import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.constants.SESSION_CACHE_PREFIX
import com.leuan.lepton.framework.common.constants.TOKEN_CACHE_PREFIX
import com.leuan.lepton.framework.common.constants.TOKEN_NAME
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.log.logDebug
import com.leuan.lepton.framework.common.log.logInfo
import com.leuan.lepton.framework.common.thread.ThreadContext
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.common.thread.setThreadContext
import com.leuan.lepton.framework.user.controller.vo.UserInfoVO
import com.leuan.lepton.framework.user.service.UserService
import jakarta.annotation.Resource
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.redisson.api.RedissonClient
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Duration

/**
 * 授权服务
 * @author yangyang
 * @date 2024/07/27
 * @constructor 创建[AuthService]
 */
@Service
class AuthService {

    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @Resource
    private lateinit var redissonClient: RedissonClient

    @Resource
    private lateinit var leptonConfig: LeptonConfig

    @Resource
    private lateinit var request: HttpServletRequest

    @Resource
    private lateinit var response: HttpServletResponse

    /**
     * 登录
     * @param [loginDTO] 登录传输层对象
     */
    fun login(loginDTO: LoginDTO): UserInfoVO {
        logDebug("用户登录：${loginDTO.phone} -> ${passwordEncoder.encode(loginDTO.password)}")
        // 判断用户名密码是否正确
        val user = userService.getUserByPhone(loginDTO.phone)
            ?: throw BizErr(BizErrEnum.PHONE_OR_PASSWORD_ERROR)

        logInfo("用户登录：${user.id} ${user.name} ${user.phone}")
        if (!passwordEncoder.matches(loginDTO.password, user.password)) throw BizErr(BizErrEnum.PHONE_OR_PASSWORD_ERROR)

        // 生成token
        val token = JWTUtil.createToken(mapOf("userId" to user.id), "lepton".toByteArray())
        logInfo("生成token：${token}")
        redissonClient.getBucket<Long>("$TOKEN_CACHE_PREFIX:${token}")
            .set(user.id, Duration.ofDays(leptonConfig.auth.tokenExpireDays))

        Cookie(TOKEN_NAME, token).also {
            it.path = "/"
            it.maxAge = (leptonConfig.auth.tokenExpireDays * 24 * 60 * 60).toInt()
            response.addCookie(it)
        }

        // 获取用户信息
        setThreadContext(ThreadContext(userId = user.id, username = user.name, token = token, tenantId = 0))
        val userInfo = userService.getUserInfo(freshCache = true)
        return userInfo
    }

    fun logout(): Boolean {
        val ctx = getThreadContext()
        redissonClient.getBucket<String>("$TOKEN_CACHE_PREFIX:${ctx.token}").delete()
        redissonClient.keys.deleteByPattern("$SESSION_CACHE_PREFIX:*:${ctx.userId}")
        return true
    }

    fun flushSession(userId: Long = getThreadContext().userId!!) = userService.getUserInfo(userId, true)
}