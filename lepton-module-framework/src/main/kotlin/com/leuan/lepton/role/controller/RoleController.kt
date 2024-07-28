package com.leuan.lepton.role.controller

import com.leuan.lepton.role.controller.dto.RoleQueryDTO
import com.leuan.lepton.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.role.service.RoleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "Role")
@RestController
@RequestMapping("role")
class RoleController {

    @Resource
    private lateinit var roleService: RoleService

    @Parameter(name = "id", description = "系统角色ID", required = true)
    @Operation(summary = "根据ID获取系统角色")
    @GetMapping("getById")
    fun getById(id: Long) = roleService.getById(id)

    @Operation(summary = "查询系统角色列表")
    @GetMapping("list")
    fun list(queryDTO: RoleQueryDTO = RoleQueryDTO()) = roleService.list(queryDTO)

    @Operation(summary = "分页查询系统角色列表")
    @GetMapping("page")
    fun page(queryDTO: RoleQueryDTO = RoleQueryDTO()) = roleService.page(queryDTO)

    @Operation(summary = "保存系统角色")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: RoleSaveDTO) = roleService.save(saveDTO)

    @Operation(summary = "根据ID删除系统角色")
    @GetMapping("deleteById")
    fun deleteById(id: Long) = roleService.deleteById(id)
}