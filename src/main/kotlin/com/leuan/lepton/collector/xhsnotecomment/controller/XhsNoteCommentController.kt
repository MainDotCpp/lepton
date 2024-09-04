package com.leuan.lepton.collector.xhsnotecomment.controller

import com.leuan.lepton.collector.xhsnotecomment.controller.dto.XhsNoteCommentQueryDTO
import com.leuan.lepton.collector.xhsnotecomment.controller.dto.XhsNoteCommentSaveDTO
import com.leuan.lepton.collector.xhsnotecomment.service.XhsNoteCommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "XhsNoteComment")
@RestController
@RequestMapping("xhsNoteComment")
class XhsNoteCommentController {

    @Resource
    private lateinit var xhsNoteCommentService: XhsNoteCommentService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取小红书评论")
    @PreAuthorize("hasAnyAuthority('collector:xhsnotecomment:menu')")
    fun getById(id: Long) = xhsNoteCommentService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询小红书评论列表")
    @PreAuthorize("hasAnyAuthority('collector:xhsnotecomment:menu')")
    fun list(queryDTO: XhsNoteCommentQueryDTO) = xhsNoteCommentService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询小红书评论列表")
    @PreAuthorize("hasAnyAuthority('collector:xhsnotecomment:menu')")
    fun page(queryDTO: XhsNoteCommentQueryDTO) = xhsNoteCommentService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存小红书评论")
    @PreAuthorize("hasAnyAuthority('collector:xhsnotecomment:update')")
    fun save(@RequestBody saveDTO: XhsNoteCommentSaveDTO) = xhsNoteCommentService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除小红书评论")
    @PreAuthorize("hasAnyAuthority('collector:xhsnotecomment:delete')")
    fun deleteById(id: Long) = xhsNoteCommentService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出小红书评论")
    @PreAuthorize("hasAnyAuthority('collector:xhsnotecomment:export')")
    fun export(queryDTO: XhsNoteCommentQueryDTO) = ""
}