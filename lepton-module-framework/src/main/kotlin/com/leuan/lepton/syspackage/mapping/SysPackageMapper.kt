package com.leuan.lepton.syspackage.mapping

import com.leuan.lepton.common.mapping.LeptonBaseMapping
import com.leuan.lepton.menu.dal.Menu
import com.leuan.lepton.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.syspackage.controller.vo.SysPackageVO
import com.leuan.lepton.syspackage.dal.SysPackage
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class SysPackageMapper {
    abstract fun toEntity(sysPackageVO: SysPackageVO): SysPackage
    abstract fun toVO(sysPackage: SysPackage): SysPackageVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: SysPackageSaveDTO, @MappingTarget sysPackage: SysPackage): SysPackage

    fun idToEntity(id: Long?): SysPackage? {
        return id?.let { SysPackage().apply { this.id = it } }
    }

    @Mapping(source = "menuIds", target = "menus")
    abstract fun saveDtoToEntity(sysPackageSaveDTO: SysPackageSaveDTO): SysPackage

    fun menuIdsToMenus(menuIds: MutableSet<Long>): MutableSet<Menu> {
        return menuIds.map { Menu().apply { id = it } }.toMutableSet()
    }

}