package com.leuan.lepton.framework.config.controller

import com.leuan.lepton.framework.config.controller.dto.ConfigQueryDTO
import com.leuan.lepton.framework.config.controller.dto.ConfigSaveDTO
import com.leuan.lepton.framework.config.dal.TenantConfig
import com.leuan.lepton.framework.config.service.ConfigService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Config")
@RestController
@RequestMapping("config")
class ConfigController {

    @Resource
    private lateinit var configService: ConfigService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取系统设置")
    @PreAuthorize("hasAnyAuthority('system:config:menu')")
    fun getById(id: Long) = configService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询系统设置列表")
    @PreAuthorize("hasAnyAuthority('system:config:menu')")
    fun list(queryDTO: ConfigQueryDTO) = configService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询系统设置列表")
    @PreAuthorize("hasAnyAuthority('system:config:menu')")
    fun page(queryDTO: ConfigQueryDTO) = configService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存系统设置")
    @PreAuthorize("hasAnyAuthority('system:config:update')")
    fun save(@RequestBody saveDTO: ConfigSaveDTO) = configService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除系统设置")
    @PreAuthorize("hasAnyAuthority('system:config:delete')")
    fun deleteById(id: Long) = configService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出系统设置")
    @PreAuthorize("hasAnyAuthority('system:config:export')")
    fun export(queryDTO: ConfigQueryDTO) = ""

    @PostMapping("saveTenantConfig")
    @Operation(summary = "保存租户配置")
    @PreAuthorize("hasAnyAuthority('system:config:update')")
    fun saveTenantConfig(@RequestBody tenantConfig: TenantConfig) = configService.saveTenantConfig(tenantConfig)

    @GetMapping("getTenantConfig")
    @Operation(summary = "获取系统配置")
    fun getSystemConfig() = configService.getConfig()

}