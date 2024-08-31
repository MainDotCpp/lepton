package com.leuan.lepton.collector.xhsnote.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.collector.xhsnote.controller.dto.XhsNoteSaveDTO
import com.leuan.lepton.collector.xhsnote.controller.dto.XhsNoteQueryDTO
import com.leuan.lepton.collector.xhsnote.controller.vo.XhsNoteVO
import com.leuan.lepton.collector.xhsnote.dal.XhsNote
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class XhsNoteMapper {
    abstract fun toEntity(xhsNoteVO: XhsNoteVO): XhsNote
    abstract fun toEntity(xhsNoteQueryDTO: XhsNoteQueryDTO): XhsNote
    abstract fun toVO(xhsNote: XhsNote): XhsNoteVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: XhsNoteSaveDTO, @MappingTarget xhsNote: XhsNote): XhsNote

    fun idToEntity(id: Long?): XhsNote? {
        return id?.let { XhsNote().apply { this.id = it } }
    }

    fun entityToId(xhsNote: XhsNote?): Long? {
        return xhsNote?.id
    }

}