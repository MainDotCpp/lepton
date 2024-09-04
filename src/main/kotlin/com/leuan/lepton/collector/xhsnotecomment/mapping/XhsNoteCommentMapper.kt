package com.leuan.lepton.collector.xhsnotecomment.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.collector.xhsnotecomment.controller.dto.XhsNoteCommentSaveDTO
import com.leuan.lepton.collector.xhsnotecomment.controller.dto.XhsNoteCommentQueryDTO
import com.leuan.lepton.collector.xhsnotecomment.controller.vo.XhsNoteCommentVO
import com.leuan.lepton.collector.xhsnotecomment.dal.XhsNoteComment
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class XhsNoteCommentMapper {
    abstract fun toVO(xhsNoteComment: XhsNoteComment): XhsNoteCommentVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: XhsNoteCommentSaveDTO, @MappingTarget xhsNoteComment: XhsNoteComment): XhsNoteComment

    fun idToEntity(id: Long?): XhsNoteComment? {
        return id?.let { XhsNoteComment().apply { this.id = it } }
    }

    fun entityToId(xhsNoteComment: XhsNoteComment?): Long? {
        return xhsNoteComment?.id
    }

}