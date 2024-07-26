package com.leuan.lepton.menu.mapping

import com.leuan.lepton.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.menu.controller.vo.MenuVO
import com.leuan.lepton.menu.dal.Menu
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class MenuMapper {
    abstract fun toEntity(menuVO: MenuVO): Menu
    abstract fun entityToVO(menu: Menu): MenuVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(menuVO: MenuVO, @MappingTarget menu: Menu): Menu

    abstract fun saveDtoToEntity(menuSaveDTO: MenuSaveDTO): Menu
}