package com.leuan.lepton.framework.dept.controller

import com.leuan.lepton.framework.dept.controller.dto.DeptQueryDTO
import com.leuan.lepton.framework.dept.controller.dto.DeptSaveDTO
import com.leuan.lepton.framework.dept.service.DeptService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Dept")
@RestController
@RequestMapping("dept")
class DeptController {

    @Resource
    private lateinit var deptService: DeptService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取部门")
    @PreAuthorize("hasAnyAuthority('system:dept:menu')")
    fun getById(id: Long) = deptService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询部门列表")
    @PreAuthorize("hasAnyAuthority('system:dept:menu')")
    fun list(queryDTO: DeptQueryDTO) = deptService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询部门列表")
    @PreAuthorize("hasAnyAuthority('system:dept:menu')")
    fun page(queryDTO: DeptQueryDTO) = deptService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存部门")
    @PreAuthorize("hasAnyAuthority('system:dept:update')")
    fun save(@RequestBody saveDTO: DeptSaveDTO) = deptService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除部门")
    @PreAuthorize("hasAnyAuthority('system:dept:delete')")
    fun deleteById(id: Long) = deptService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出部门")
    @PreAuthorize("hasAnyAuthority('system:dept:export')")
    fun export(queryDTO: DeptQueryDTO) = ""

    @Operation(summary = "获取系统部门树")
    @GetMapping("tree")
    fun tree() = deptService.deptTree()
}