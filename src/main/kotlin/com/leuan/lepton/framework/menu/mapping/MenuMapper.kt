package com.leuan.lepton.framework.menu.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.framework.menu.controller.vo.MenuVO
import com.leuan.lepton.framework.menu.dal.Menu
import com.leuan.lepton.framework.menu.enums.MenuTypeEnum
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

    fun EntityToId(entity: Menu?): Long? {
        return entity?.id
    }

    fun menusToMenuIds(menus: MutableSet<Menu>): MutableSet<Long> {
        return menus.filter { it.type != MenuTypeEnum.CATALOG }.map { it.id!! }.toMutableSet()
    }

    fun menuIdsToMenus(menuIds: MutableSet<Long>): MutableSet<Menu> {
        return menuIds.map { Menu().apply { id = it } }.toMutableSet()
    }
}