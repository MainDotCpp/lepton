package com.leuan.lepton.framework.common.config

import com.leuan.lepton.framework.auth.filter.SecurityJwtFilter
import com.leuan.lepton.framework.auth.service.LeptonUserDetailService
import com.leuan.lepton.framework.common.http.fail
import com.leuan.lepton.framework.common.utils.toJson
import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    @Resource
    private lateinit var leptonConfig: LeptonConfig

    @Resource
    private lateinit var authenticationConfiguration: AuthenticationConfiguration

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()


    /**
     * 认证管理器，登录的时候参数会传给 authenticationManager
     */
    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    /**
     * 安全过滤链
     * @param [http] http
     * @param [securityJwtFilter] 安全 jwt 过滤器
     * @return [SecurityFilterChain]
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity, leptonUserDetailService: LeptonUserDetailService): SecurityFilterChain {
        http
            // 关闭csrf
            .csrf { it.disable() }
            // 由于session保存在redis中，所以将spring security的session策略设置为无状态
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationManager(authenticationManager(authenticationConfiguration))
            // 配置请求授权
            .authorizeHttpRequests {
                it
                    .requestMatchers(*leptonConfig.ignoreLoginUrl.toTypedArray()).permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, e ->
                    response.contentType = "application/json;charset=utf-8"
                    val out = response.writer
                    out.write(fail<Any?>(401, "未登录").toJson())
                    out.flush()
                    out.close()
                }
                it.accessDeniedHandler { request, response, accessDeniedException ->
                    response.contentType = "application/json;charset=utf-8"
                    val out = response.writer
                    out.write(fail<Any?>(403, "权限不足!").toJson())
                    out.flush()
                    out.close()
                }
            }
            // 添加自定义过滤器，放在UsernamePasswordAuthenticationFilter之前
            .addFilterBefore(
                SecurityJwtFilter(leptonUserDetailService, leptonConfig),
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}