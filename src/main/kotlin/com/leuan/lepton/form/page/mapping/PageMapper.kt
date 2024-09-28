package com.leuan.lepton.form.page.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.form.page.controller.dto.PageSaveDTO
import com.leuan.lepton.form.page.controller.vo.PageVO
import com.leuan.lepton.form.page.dal.Page
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class PageMapper {
    abstract fun toVO(page: Page): PageVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: PageSaveDTO, @MappingTarget page: Page): Page

    fun idToEntity(id: Long?): Page? {
        return id?.let { Page().apply { this.id = it } }
    }

    fun entityToId(page: Page?): Long? {
        return page?.id
    }

}