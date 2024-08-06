package com.leuan.lepton.framework.auth.service

import com.leuan.lepton.framework.user.service.UserService
import jakarta.annotation.Resource
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class LeptonUserDetailService : UserDetailsService {

    @Resource
    private lateinit var userService: UserService
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw IllegalArgumentException("用户名不能为空")
        }
        return userService.getUserInfo(username.toLong())
    }
}