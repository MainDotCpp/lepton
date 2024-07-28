package com.leuan.lepton.auth.filter

import cn.hutool.jwt.JWTUtil
import com.leuan.lepton.auth.service.LeptonUserDetailService
import com.leuan.lepton.common.constants.BizErrEnum
import com.leuan.lepton.common.constants.TOKEN_CACHE_PREFIX
import com.leuan.lepton.common.constants.TOKEN_NAME
import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.thread.ThreadContext
import com.leuan.lepton.common.thread.clearThreadContext
import com.leuan.lepton.common.thread.setThreadContext
import com.leuan.lepton.user.controller.vo.UserInfoVO
import jakarta.annotation.Resource
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.redisson.api.RedissonClient
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityJwtFilter : OncePerRequestFilter() {

    @Resource
    private lateinit var redissonClient: RedissonClient

    @Resource
    private lateinit var leptonUserDetailService: LeptonUserDetailService
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 获取请求头中的token,可能在header中或者cookie中
        val token = request.getHeader(TOKEN_NAME) ?: request.cookies?.find { it.name == TOKEN_NAME }?.value

        // 如果token为空，直接放行
        if (token == null) {
            filterChain.doFilter(request, response)
            return
        }

        // 如果token不为空，解析token，获取用户信息
        if (JWTUtil.verify(token, "lepton".toByteArray()).not()) {
            throw IllegalArgumentException("token验证失败")
        }

        val userId = redissonClient.getBucket<String>("$TOKEN_CACHE_PREFIX:$token").run {
            if (!isExists) throw BizErr(BizErrEnum.LOGIN_EXPIRED)
            get()
        }

        val userInfo = leptonUserDetailService.loadUserByUsername(userId) as UserInfoVO
        val authenticationToken = UsernamePasswordAuthenticationToken(userInfo, null, userInfo.authorities)
        SecurityContextHolder.getContext().authentication = authenticationToken
        logInfo("用户${userInfo.id}已登录")

        setThreadContext(ThreadContext(0L, userInfo.id, userInfo.name))
        filterChain.doFilter(request, response)
        clearThreadContext()
    }
}