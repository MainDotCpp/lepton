package com.leuan.lepton.syspackage.controller

import com.leuan.lepton.syspackage.controller.dto.SysPackageQueryDTO
import com.leuan.lepton.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.syspackage.service.SysPackageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "SysPackage")
@RestController
@RequestMapping("sysPackage")
class SysPackageController {

    @Resource
    private lateinit var sysPackageService: SysPackageService

    @Parameter(name = "id", description = "系统套餐ID", required = true)
    @Operation(summary = "根据ID获取系统套餐")
    @GetMapping("getById")
    fun getById(id: Long) = sysPackageService.getById(id)

    @Operation(summary = "查询系统套餐列表")
    @GetMapping("list")
    fun list(queryDTO: SysPackageQueryDTO = SysPackageQueryDTO()) = sysPackageService.list(queryDTO)

    @Operation(summary = "分页查询系统套餐列表")
    @GetMapping("page")
    fun page(queryDTO: SysPackageQueryDTO = SysPackageQueryDTO()) = sysPackageService.page(queryDTO)

    @Operation(summary = "保存系统套餐")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: SysPackageSaveDTO) = sysPackageService.save(saveDTO)

    @Operation(summary = "根据ID删除系统套餐")
    @GetMapping("deleteById")
    fun deleteById(id: Long) = sysPackageService.deleteById(id)
}