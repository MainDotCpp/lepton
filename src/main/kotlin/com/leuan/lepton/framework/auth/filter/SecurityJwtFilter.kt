package com.leuan.lepton.framework.auth.filter

import cn.hutool.jwt.JWTUtil
import com.leuan.lepton.framework.auth.service.LeptonUserDetailService
import com.leuan.lepton.framework.common.config.LeptonConfig
import com.leuan.lepton.framework.common.constants.TOKEN_CACHE_PREFIX
import com.leuan.lepton.framework.common.constants.TOKEN_NAME
import com.leuan.lepton.framework.common.log.logError
import com.leuan.lepton.framework.common.thread.ThreadContext
import com.leuan.lepton.framework.common.thread.clearThreadContext
import com.leuan.lepton.framework.common.thread.setThreadContext
import com.leuan.lepton.framework.common.utils.redissonClient
import com.leuan.lepton.framework.user.controller.vo.UserInfoVO
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

class SecurityJwtFilter(
    private val leptonUserDetailService: LeptonUserDetailService,
    private val leptonConfig: LeptonConfig
) : OncePerRequestFilter() {

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
            filterChain.doFilter(request, response)
            return
        }

        val userId = redissonClient.getBucket<String>("$TOKEN_CACHE_PREFIX:$token").run {
            if (!isExists) {
                filterChain.doFilter(request, response)
                return
            }
            get()
        }


        // 初始化上下文
        val context = ThreadContext(
            userId = userId.toLong(),
            username = "",
            token = token,
            tenantId = request.getHeader("tenant-id")?.toLong() ?: 0L
        )
        setThreadContext(context)

        // 获取用户信息
        val userInfo = leptonUserDetailService.loadUserByUsername(userId) as UserInfoVO
        context.username = userInfo.username


        // 判断是否忽略租户id
        context.ignoreTenantId =
            leptonConfig.ignoreTenantUrl.any { pattern -> AntPathMatcher().match(pattern, request.requestURI) }

        // 如果不是忽略租户id, 却又没有租户id, 直接返回
        if (!context.ignoreTenantId && context.tenantId == 0L) {
            logError("租户id为空")
            filterChain.doFilter(request, response)
            return
        }

        if (context.tenantId != 0L && userInfo.tenants.map { it.id }.none { it == context.tenantId }) {
            logError("非法操作")
            filterChain.doFilter(request, response)
            return
        }
        // 校验流程全部通过, 设置用户信息到spring security上下文

        MDC.put("userId", userId)
        MDC.put("userName", context.username)
        MDC.put("tenantId", context.tenantId.toString())

        val authenticationToken = UsernamePasswordAuthenticationToken(userInfo, null, userInfo.authorities)
        SecurityContextHolder.getContext().authentication = authenticationToken
        filterChain.doFilter(request, response)
        clearThreadContext()
    }
}