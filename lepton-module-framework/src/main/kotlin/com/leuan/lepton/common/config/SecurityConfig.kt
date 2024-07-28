package com.leuan.lepton.common.config

import com.leuan.lepton.auth.filter.SecurityJwtFilter
import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Resource
    private lateinit var leptonConfig: LeptonConfig

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

//    @Bean
//    fun ignoringCustomizer(leptonConfig: LeptonConfig): WebSecurityCustomizer {
//        return WebSecurityCustomizer { web: WebSecurity ->
//            val ignoring = web.ignoring()
//            leptonConfig.ignoreLoginUrl.map { ignoring.requestMatchers(it) }
//        }
//    }

    /**
     * 安全过滤链
     * @param [http] http
     * @param [securityJwtFilter] 安全 jwt 过滤器
     * @return [SecurityFilterChain]
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity, securityJwtFilter: SecurityJwtFilter): SecurityFilterChain {
        http
            // 添加自定义过滤器，放在UsernamePasswordAuthenticationFilter之前
            .addFilterBefore(securityJwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            // 关闭csrf
            .csrf { it.disable() }
            // 由于session保存在redis中，所以将spring security的session策略设置为无状态
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            // 配置请求授权
            .authorizeHttpRequests {
                it.requestMatchers(*leptonConfig.ignoreLoginUrl.toTypedArray()).permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }
}