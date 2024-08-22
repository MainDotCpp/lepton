package com.leuan.lepton.framework.dict.controller

import com.leuan.lepton.framework.dict.controller.dto.DictQueryDTO
import com.leuan.lepton.framework.dict.controller.dto.DictSaveDTO
import com.leuan.lepton.framework.dict.service.DictService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Dict")
@RestController
@RequestMapping("dict")
class DictController {

    @Resource
    private lateinit var dictService: DictService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取字典")
    @PreAuthorize("hasAnyAuthority('system:dict:menu')")
    fun getById(id: Long) = dictService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询字典列表")
    fun list(queryDTO: DictQueryDTO) = dictService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询字典列表")
    @PreAuthorize("hasAnyAuthority('system:dict:create')")
    fun page(queryDTO: DictQueryDTO) = dictService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存字典")
    @PreAuthorize("hasAnyAuthority('system:dict:update')")
    fun save(@RequestBody saveDTO: DictSaveDTO) = dictService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除字典")
    @PreAuthorize("hasAnyAuthority('system:dict:delete')")
    fun deleteById(id: Long) = dictService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出字典")
    @PreAuthorize("hasAnyAuthority('system:dict:export')")
    fun export(queryDTO: DictQueryDTO) = ""
}