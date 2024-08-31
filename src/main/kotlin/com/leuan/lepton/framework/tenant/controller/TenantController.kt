package com.leuan.lepton.framework.tenant.controller

import com.leuan.lepton.framework.tenant.controller.dto.TenantQueryDTO
import com.leuan.lepton.framework.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.framework.tenant.controller.vo.TenantVO
import com.leuan.lepton.framework.tenant.service.TenantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Tenant")
@RestController
@RequestMapping("tenant")
class TenantController {

    @Resource
    private lateinit var tenantService: TenantService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取租户")
    @PreAuthorize("hasAnyAuthority('system:tenant:menu')")
    fun getById(id: Long) = tenantService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询租户列表")
    @PreAuthorize("hasAnyAuthority('system:tenant:menu')")
    fun list(queryDTO: TenantQueryDTO): List<TenantVO> = tenantService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询租户列表")
    @PreAuthorize("hasAnyAuthority('system:tenant:menu')")
    fun page(queryDTO: TenantQueryDTO) = tenantService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存租户")
    @PreAuthorize("hasAnyAuthority('system:tenant:update')")
    fun save(@RequestBody saveDTO: TenantSaveDTO) = tenantService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除租户")
    @PreAuthorize("hasAnyAuthority('system:tenant:delete')")
    fun deleteById(id: Long) = tenantService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出租户")
    @PreAuthorize("hasAnyAuthority('system:tenant:export')")
    fun export(queryDTO: TenantQueryDTO) = ""
}