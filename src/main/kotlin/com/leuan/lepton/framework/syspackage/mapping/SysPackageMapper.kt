package com.leuan.lepton.framework.syspackage.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.menu.mapping.MenuMapper
import com.leuan.lepton.framework.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.framework.syspackage.controller.vo.SysPackageVO
import com.leuan.lepton.framework.syspackage.dal.SysPackage
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class, MenuMapper::class]
)
abstract class SysPackageMapper {
    abstract fun toEntity(sysPackageVO: SysPackageVO): SysPackage

    @Mapping(source = "menus", target = "menuIds")
    abstract fun toVO(sysPackage: SysPackage): SysPackageVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: SysPackageSaveDTO, @MappingTarget sysPackage: SysPackage): SysPackage

    fun idToEntity(id: Long?): SysPackage? {
        return id?.let { SysPackage().apply { this.id = it } }
    }

    @Mapping(source = "menuIds", target = "menus")
    abstract fun saveDtoToEntity(sysPackageSaveDTO: SysPackageSaveDTO): SysPackage


}