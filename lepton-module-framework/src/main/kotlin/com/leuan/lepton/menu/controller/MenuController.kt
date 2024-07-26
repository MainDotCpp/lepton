package com.leuan.lepton.menu.controller

import com.leuan.lepton.menu.controller.dto.MenuQueryDTO
import com.leuan.lepton.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.menu.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "系统菜单")
@RestController
@RequestMapping("menu")
class MenuController {

    @Resource
    private lateinit var menuService: MenuService

    @Parameter(name = "id", description = "系统菜单ID", required = true)
    @Operation(summary = "根据ID获取系统菜单")
    @GetMapping("getById")
    fun getById(id: Long) = menuService.getById(id)

    @Operation(summary = "查询系统菜单列表")
    @GetMapping("list")
    fun list(queryDTO: MenuQueryDTO = MenuQueryDTO()) = menuService.list(queryDTO)

    @Operation(summary = "分页查询系统菜单列表")
    @GetMapping("page")
    fun page(queryDTO: MenuQueryDTO = MenuQueryDTO()) = menuService.page(queryDTO)

    @Operation(summary = "保存系统菜单")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: MenuSaveDTO) = menuService.save(saveDTO)

    @Operation(summary = "根据ID删除系统菜单")
    @GetMapping("delete")
    fun deleteById(id: Long) = menuService.deleteById(id)

    @Operation(summary = "获取系统菜单树")
    @GetMapping("tree")
    fun tree() = menuService.getMenuTree()
}