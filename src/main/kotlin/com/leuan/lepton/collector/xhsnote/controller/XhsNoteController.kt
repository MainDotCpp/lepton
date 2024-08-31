package com.leuan.lepton.collector.xhsnote.controller

import com.leuan.lepton.collector.xhsnote.controller.dto.XhsNoteQueryDTO
import com.leuan.lepton.collector.xhsnote.controller.dto.XhsNoteSaveDTO
import com.leuan.lepton.collector.xhsnote.service.XhsNoteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "XhsNote")
@RestController
@RequestMapping("xhsNote")
class XhsNoteController {

    @Resource
    private lateinit var xhsNoteService: XhsNoteService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取小红书笔记")
    @PreAuthorize("hasAnyAuthority('collector:xhsnote:menu')")
    fun getById(id: Long) = xhsNoteService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询小红书笔记列表")
    fun list(queryDTO: XhsNoteQueryDTO) = xhsNoteService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询小红书笔记列表")
//    @PreAuthorize("hasAnyAuthority('collector:xhsnote:create')")
    fun page(queryDTO: XhsNoteQueryDTO) = xhsNoteService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存小红书笔记")
    @PreAuthorize("hasAnyAuthority('collector:xhsnote:update')")
    fun save(@RequestBody saveDTO: XhsNoteSaveDTO) = xhsNoteService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除小红书笔记")
    @PreAuthorize("hasAnyAuthority('collector:xhsnote:delete')")
    fun deleteById(id: Long) = xhsNoteService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出小红书笔记")
    @PreAuthorize("hasAnyAuthority('collector:xhsnote:export')")
    fun export(queryDTO: XhsNoteQueryDTO) = ""
}