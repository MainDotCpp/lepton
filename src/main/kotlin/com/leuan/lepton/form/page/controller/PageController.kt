package com.leuan.lepton.form.page.controller

import com.leuan.lepton.form.page.controller.dto.PageQueryDTO
import com.leuan.lepton.form.page.controller.dto.PageSaveDTO
import com.leuan.lepton.form.page.service.PageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Page")
@RestController
@RequestMapping("page")
class PageController {

    @Resource
    private lateinit var pageService: PageService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取页面")
    @PreAuthorize("hasAnyAuthority('form:page:menu')")
    fun getById(id: Long) = pageService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询页面列表")
    @PreAuthorize("hasAnyAuthority('form:page:menu')")
    fun list(queryDTO: PageQueryDTO) = pageService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询页面列表")
    @PreAuthorize("hasAnyAuthority('form:page:menu')")
    fun page(queryDTO: PageQueryDTO) = pageService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存页面")
    @PreAuthorize("hasAnyAuthority('form:page:update')")
    fun save(@RequestBody saveDTO: PageSaveDTO) = pageService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除页面")
    @PreAuthorize("hasAnyAuthority('form:page:delete')")
    fun deleteById(id: Long) = pageService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出页面")
    @PreAuthorize("hasAnyAuthority('form:page:export')")
    fun export(queryDTO: PageQueryDTO) = ""
}