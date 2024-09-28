package com.leuan.lepton.form.component.controller

import com.leuan.lepton.form.component.controller.dto.ComponentQueryDTO
import com.leuan.lepton.form.component.controller.dto.ComponentSaveDTO
import com.leuan.lepton.form.component.service.ComponentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Component")
@RestController
@RequestMapping("component")
class ComponentController {

    @Resource
    private lateinit var componentService: ComponentService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取表单组件")
    @PreAuthorize("hasAnyAuthority('form:component:menu')")
    fun getById(id: Long) = componentService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询表单组件列表")
    @PreAuthorize("hasAnyAuthority('form:component:menu')")
    fun list(queryDTO: ComponentQueryDTO) = componentService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询表单组件列表")
    @PreAuthorize("hasAnyAuthority('form:component:menu')")
    fun page(queryDTO: ComponentQueryDTO) = componentService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存表单组件")
    @PreAuthorize("hasAnyAuthority('form:component:update')")
    fun save(@RequestBody saveDTO: ComponentSaveDTO) = componentService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除表单组件")
    @PreAuthorize("hasAnyAuthority('form:component:delete')")
    fun deleteById(id: Long) = componentService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出表单组件")
    @PreAuthorize("hasAnyAuthority('form:component:export')")
    fun export(queryDTO: ComponentQueryDTO) = ""
}