package com.leuan.lepton.menu.mapping

import com.leuan.lepton.common.mapping.LeptonBaseMapping
import com.leuan.lepton.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.menu.controller.vo.MenuVO
import com.leuan.lepton.menu.dal.Menu
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class MenuMapper {
    abstract fun toEntity(menuVO: MenuVO): Menu
    abstract fun toVO(menu: Menu): MenuVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: MenuSaveDTO, @MappingTarget menu: Menu): Menu

    fun idToEntity(id: Long?): Menu? {
        return id?.let { Menu().apply { this.id = it } }
    }

}