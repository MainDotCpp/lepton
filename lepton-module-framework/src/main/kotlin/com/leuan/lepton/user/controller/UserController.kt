package com.leuan.lepton.user.controller

import com.leuan.lepton.user.controller.dto.UserQueryDTO
import com.leuan.lepton.user.controller.dto.UserSaveDTO
import com.leuan.lepton.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "系统用户")
@RestController
@RequestMapping("user")
class UserController {

    @Resource
    private lateinit var userService: UserService

    @Parameter(name = "id", description = "系统用户ID", required = true)
    @Operation(summary = "根据ID获取系统用户")
    @GetMapping("getById")
    fun getById(id: Long) = userService.getById(id)

    @Operation(summary = "查询系统用户列表")
    @GetMapping("list")
    fun list(queryDTO: UserQueryDTO = UserQueryDTO()) = userService.list(queryDTO)

    @Operation(summary = "分页查询系统用户列表")
    @GetMapping("page")
    fun page(queryDTO: UserQueryDTO = UserQueryDTO()) = userService.page(queryDTO)

    @Operation(summary = "保存系统用户")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: UserSaveDTO) = userService.save(saveDTO)

    @Operation(summary = "根据ID删除系统用户")
    @GetMapping("delete")
    fun deleteById(id: Long) = userService.deleteById(id)
}