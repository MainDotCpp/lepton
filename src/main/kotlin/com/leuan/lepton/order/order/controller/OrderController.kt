package com.leuan.lepton.order.order.controller

import com.leuan.lepton.order.order.controller.dto.OrderQueryDTO
import com.leuan.lepton.order.order.controller.dto.OrderSaveDTO
import com.leuan.lepton.order.order.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Order")
@RestController
@RequestMapping("order")
class OrderController {

    @Resource
    private lateinit var orderService: OrderService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取订单")
    @PreAuthorize("hasAnyAuthority('order:order:menu')")
    fun getById(id: Long) = orderService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询订单列表")
    @PreAuthorize("hasAnyAuthority('order:order:menu')")
    fun list(queryDTO: OrderQueryDTO) = orderService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询订单列表")
    @PreAuthorize("hasAnyAuthority('order:order:menu')")
    fun page(queryDTO: OrderQueryDTO) = orderService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存订单")
    @PreAuthorize("hasAnyAuthority('order:order:update')")
    fun save(@RequestBody saveDTO: OrderSaveDTO) = orderService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除订单")
    @PreAuthorize("hasAnyAuthority('order:order:delete')")
    fun deleteById(id: Long) = orderService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出订单")
    @PreAuthorize("hasAnyAuthority('order:order:export')")
    fun export(queryDTO: OrderQueryDTO) = ""
}