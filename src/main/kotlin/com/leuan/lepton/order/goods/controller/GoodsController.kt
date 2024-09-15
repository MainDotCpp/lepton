package com.leuan.lepton.order.goods.controller

import com.leuan.lepton.order.goods.controller.dto.GoodsQueryDTO
import com.leuan.lepton.order.goods.controller.dto.GoodsSaveDTO
import com.leuan.lepton.order.goods.service.GoodsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Goods")
@RestController
@RequestMapping("goods")
class GoodsController {

    @Resource
    private lateinit var goodsService: GoodsService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取商品")
    @PreAuthorize("hasAnyAuthority('order:goods:menu')")
    fun getById(id: Long) = goodsService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询商品列表")
    @PreAuthorize("hasAnyAuthority('order:goods:menu','order:order:update')")
    fun list(queryDTO: GoodsQueryDTO) = goodsService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询商品列表")
    @PreAuthorize("hasAnyAuthority('order:goods:menu')")
    fun page(queryDTO: GoodsQueryDTO) = goodsService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存商品")
    @PreAuthorize("hasAnyAuthority('order:goods:update')")
    fun save(@RequestBody saveDTO: GoodsSaveDTO) = goodsService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除商品")
    @PreAuthorize("hasAnyAuthority('order:goods:delete')")
    fun deleteById(id: Long) = goodsService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出商品")
    @PreAuthorize("hasAnyAuthority('order:goods:export')")
    fun export(queryDTO: GoodsQueryDTO) = ""
}