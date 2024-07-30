package com.leuan.lepton.tenant.controller

import com.leuan.lepton.tenant.controller.dto.TenantQueryDTO
import com.leuan.lepton.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.tenant.service.TenantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "Tenant")
@RestController
@RequestMapping("tenant")
class TenantController {

    @Resource
    private lateinit var tenantService: TenantService

    @Parameter(name = "id", description = "租户ID", required = true)
    @Operation(summary = "根据ID获取租户")
    @GetMapping("getById")
    fun getById(id: Long) = tenantService.getById(id)

    @Operation(summary = "查询租户列表")
    @GetMapping("list")
    fun list(queryDTO: TenantQueryDTO = TenantQueryDTO()) = tenantService.list(queryDTO)

    @Operation(summary = "分页查询租户列表")
    @GetMapping("page")
    fun page(queryDTO: TenantQueryDTO = TenantQueryDTO()) = tenantService.page(queryDTO)

    @Operation(summary = "保存租户")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: TenantSaveDTO) = tenantService.save(saveDTO)

    @Operation(summary = "根据ID删除租户")
    @GetMapping("deleteById")
    fun deleteById(id: Long) = tenantService.deleteById(id)
}