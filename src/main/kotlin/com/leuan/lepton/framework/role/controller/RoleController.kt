package com.leuan.lepton.framework.role.controller

import com.leuan.lepton.framework.role.controller.dto.RoleQueryDTO
import com.leuan.lepton.framework.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.framework.role.service.RoleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Role")
@RestController
@RequestMapping("role")
class RoleController {

    @Resource
    private lateinit var roleService: RoleService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取角色")
    @PreAuthorize("hasAnyAuthority('system:role:menu')")
    fun getById(id: Long) = roleService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询角色列表")
    @PreAuthorize("hasAnyAuthority('system:role:menu')")
    fun list(queryDTO: RoleQueryDTO = RoleQueryDTO()) = roleService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询角色列表")
    @PreAuthorize("hasAnyAuthority('system:role:create')")
    fun page(queryDTO: RoleQueryDTO = RoleQueryDTO()) = roleService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存角色")
    @PreAuthorize("hasAnyAuthority('system:role:update')")
    fun save(@RequestBody saveDTO: RoleSaveDTO) = roleService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除角色")
    @PreAuthorize("hasAnyAuthority('system:role:delete')")
    fun deleteById(id: Long) = roleService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出角色")
    @PreAuthorize("hasAnyAuthority('system:role:export')")
    fun export(queryDTO: RoleQueryDTO = RoleQueryDTO()) = ""
}