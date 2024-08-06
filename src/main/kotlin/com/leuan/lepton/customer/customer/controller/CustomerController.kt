package com.leuan.lepton.customer.customer.controller

import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Customer")
@RestController
@RequestMapping("customer")
class CustomerController {

    @Resource
    private lateinit var customerService: CustomerService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取客资")
    @PreAuthorize("hasAnyAuthority('customer:customer:menu')")
    fun getById(id: Long) = customerService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询客资列表")
    @PreAuthorize("hasAnyAuthority('customer:customer:menu')")
    fun list(queryDTO: CustomerQueryDTO) = customerService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询客资列表")
    @PreAuthorize("hasAnyAuthority('customer:customer:create')")
    fun page(queryDTO: CustomerQueryDTO) = customerService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存客资")
    @PreAuthorize("hasAnyAuthority('customer:customer:update')")
    fun save(@RequestBody saveDTO: CustomerSaveDTO) = customerService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除客资")
    @PreAuthorize("hasAnyAuthority('customer:customer:delete')")
    fun deleteById(id: Long) = customerService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出客资")
    @PreAuthorize("hasAnyAuthority('customer:customer:export')")
    fun export(queryDTO: CustomerQueryDTO) = ""
}