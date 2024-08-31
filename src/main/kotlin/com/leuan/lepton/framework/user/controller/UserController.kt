package com.leuan.lepton.framework.user.controller

import com.leuan.lepton.framework.user.controller.dto.UserQueryDTO
import com.leuan.lepton.framework.user.controller.dto.UserSaveDTO
import com.leuan.lepton.framework.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "User")
@RestController
@RequestMapping("user")
class UserController {

    @Resource
    private lateinit var userService: UserService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取用户")
    @PreAuthorize("hasAnyAuthority('system:user:menu')")
    fun getById(id: Long) = userService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询用户列表")
    @PreAuthorize("hasAnyAuthority('system:user:menu')")
    fun list(queryDTO: UserQueryDTO = UserQueryDTO()) = userService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询用户列表")
    @PreAuthorize("hasAnyAuthority('system:user:create')")
    fun page(queryDTO: UserQueryDTO = UserQueryDTO()) = userService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存用户")
    @PreAuthorize("hasAnyAuthority('system:user:update')")
    fun save(@RequestBody saveDTO: UserSaveDTO) = userService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除用户")
    @PreAuthorize("hasAnyAuthority('system:user:delete')")
    fun deleteById(id: Long) = userService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出用户")
    @PreAuthorize("hasAnyAuthority('system:user:export')")
    fun export(queryDTO: UserQueryDTO = UserQueryDTO()) = ""

    @GetMapping("userInfo")
    @Operation(summary = "获取用户信息")
    fun userInfo() = userService.getUserInfo()

    @GetMapping("userOptions")
    @Operation(summary = "获取用户选项")
    fun userOptions() = userService.getUserOptions()
}