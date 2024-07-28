package com.leuan.lepton.customer.customer.controller

import com.leuan.lepton.customer.customer.controller.dto.CustomerQueryDTO
import com.leuan.lepton.customer.customer.controller.dto.CustomerSaveDTO
import com.leuan.lepton.customer.customer.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "Customer")
@RestController
@RequestMapping("customer")
class CustomerController {

    @Resource
    private lateinit var customerService: CustomerService

    @Parameter(name = "id", description = "客资ID", required = true)
    @Operation(summary = "根据ID获取客资")
    @GetMapping("getById")
    fun getById(id: Long) = customerService.getById(id)

    @Operation(summary = "查询客资列表")
    @GetMapping("list")
    fun list(queryDTO: CustomerQueryDTO = CustomerQueryDTO()) = customerService.list(queryDTO)

    @Operation(summary = "分页查询客资列表")
    @GetMapping("page")
    fun page(queryDTO: CustomerQueryDTO = CustomerQueryDTO()) = customerService.page(queryDTO)

    @Operation(summary = "保存客资")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: CustomerSaveDTO) = customerService.save(saveDTO)

    @Operation(summary = "根据ID删除客资")
    @GetMapping("deleteById")
    fun deleteById(id: Long) = customerService.deleteById(id)
}