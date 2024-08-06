package com.leuan.lepton.framework.syspackage.controller

import com.leuan.lepton.framework.syspackage.controller.dto.SysPackageQueryDTO
import com.leuan.lepton.framework.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.framework.syspackage.service.SysPackageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "SysPackage")
@RestController
@RequestMapping("sysPackage")
class SysPackageController {

    @Resource
    private lateinit var sysPackageService: SysPackageService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取系统套餐")
    @PreAuthorize("hasAnyAuthority('system:sysPackage:menu')")
    fun getById(id: Long) = sysPackageService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询系统套餐列表")
    @PreAuthorize("hasAnyAuthority('system:sysPackage:menu')")
    fun list(queryDTO: SysPackageQueryDTO = SysPackageQueryDTO()) = sysPackageService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询系统套餐列表")
    @PreAuthorize("hasAnyAuthority('system:sysPackage:create')")
    fun page(queryDTO: SysPackageQueryDTO = SysPackageQueryDTO()) = sysPackageService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存系统套餐")
    @PreAuthorize("hasAnyAuthority('system:sysPackage:update')")
    fun save(@RequestBody saveDTO: SysPackageSaveDTO) = sysPackageService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除系统套餐")
    @PreAuthorize("hasAnyAuthority('system:sysPackage:delete')")
    fun deleteById(id: Long) = sysPackageService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出系统套餐")
    @PreAuthorize("hasAnyAuthority('system:sysPackage:export')")
    fun export(queryDTO: SysPackageQueryDTO = SysPackageQueryDTO()) = ""
}