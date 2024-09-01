package com.leuan.lepton.customer.brand.controller

import com.leuan.lepton.customer.brand.controller.dto.BrandQueryDTO
import com.leuan.lepton.customer.brand.controller.dto.BrandSaveDTO
import com.leuan.lepton.customer.brand.service.BrandService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Brand")
@RestController
@RequestMapping("brand")
class BrandController {

    @Resource
    private lateinit var brandService: BrandService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取品牌")
    @PreAuthorize("hasAnyAuthority('customer:brand:menu')")
    fun getById(id: Long) = brandService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询品牌列表")
    @PreAuthorize("hasAnyAuthority('customer:brand:menu')")
    fun list(queryDTO: BrandQueryDTO) = brandService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询品牌列表")
    @PreAuthorize("hasAnyAuthority('customer:brand:menu')")
    fun page(queryDTO: BrandQueryDTO) = brandService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存品牌")
    @PreAuthorize("hasAnyAuthority('customer:brand:update')")
    fun save(@RequestBody saveDTO: BrandSaveDTO) = brandService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除品牌")
    @PreAuthorize("hasAnyAuthority('customer:brand:delete')")
    fun deleteById(id: Long) = brandService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出品牌")
    @PreAuthorize("hasAnyAuthority('customer:brand:export')")
    fun export(queryDTO: BrandQueryDTO) = ""
}