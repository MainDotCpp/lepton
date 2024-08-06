package com.leuan.lepton.framework.auth.controller

import com.leuan.lepton.framework.auth.controller.dto.LoginDTO
import com.leuan.lepton.framework.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
class AuthController {

    @Resource
    private lateinit var authService: AuthService

    @Operation(summary = "登录")
    @PostMapping("/login")
    fun login(@RequestBody loginDTO: LoginDTO) = authService.login(loginDTO)

    @Operation(summary = "登出")
    @PostMapping("/logout")
    fun logout() = authService.logout()
}