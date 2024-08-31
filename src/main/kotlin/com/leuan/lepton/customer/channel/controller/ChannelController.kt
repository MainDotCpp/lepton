package com.leuan.lepton.customer.channel.controller

import com.leuan.lepton.customer.channel.controller.dto.ChannelQueryDTO
import com.leuan.lepton.customer.channel.controller.dto.ChannelSaveDTO
import com.leuan.lepton.customer.channel.service.ChannelService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "Channel")
@RestController
@RequestMapping("channel")
class ChannelController {

    @Resource
    private lateinit var channelService: ChannelService

    @GetMapping("getById")
    @Operation(summary = "根据ID获取客资渠道")
    @PreAuthorize("hasAnyAuthority('customer:channel:menu')")
    fun getById(id: Long) = channelService.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询客资渠道列表")
    @PreAuthorize("hasAnyAuthority('customer:channel:menu')")
    fun list(queryDTO: ChannelQueryDTO) = channelService.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询客资渠道列表")
    @PreAuthorize("hasAnyAuthority('customer:channel:menu')")
    fun page(queryDTO: ChannelQueryDTO) = channelService.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存客资渠道")
    @PreAuthorize("hasAnyAuthority('customer:channel:update')")
    fun save(@RequestBody saveDTO: ChannelSaveDTO) = channelService.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除客资渠道")
    @PreAuthorize("hasAnyAuthority('customer:channel:delete')")
    fun deleteById(id: Long) = channelService.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出客资渠道")
    @PreAuthorize("hasAnyAuthority('customer:channel:export')")
    fun export(queryDTO: ChannelQueryDTO) = ""
}