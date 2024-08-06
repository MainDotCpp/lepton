package com.leuan.lepton.framework.menu.controller

import com.leuan.lepton.framework.menu.controller.dto.MenuQueryDTO
import com.leuan.lepton.framework.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.framework.menu.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Menu")
@RestController
@RequestMapping("menu")
class MenuController {

    @Resource
    private lateinit var menuService: MenuService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取菜单")
    @PreAuthorize("hasAnyAuthority('system:menu:menu')")
    fun getById(id: Long) = menuService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询菜单列表")
    @PreAuthorize("hasAnyAuthority('system:menu:menu')")
    fun list(queryDTO: MenuQueryDTO = MenuQueryDTO()) = menuService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询菜单列表")
    @PreAuthorize("hasAnyAuthority('system:menu:create')")
    fun page(queryDTO: MenuQueryDTO = MenuQueryDTO()) = menuService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存菜单")
    @PreAuthorize("hasAnyAuthority('system:menu:update')")
    fun save(@RequestBody saveDTO: MenuSaveDTO) = menuService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除菜单")
    @PreAuthorize("hasAnyAuthority('system:menu:delete')")
    fun deleteById(id: Long) = menuService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出菜单")
    @PreAuthorize("hasAnyAuthority('system:menu:export')")
    fun export(queryDTO: MenuQueryDTO = MenuQueryDTO()) = ""

    @Operation(summary = "获取系统菜单树")
    @GetMapping("tree")
    fun tree() = menuService.getMenuTree()
}