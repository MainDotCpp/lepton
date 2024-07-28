package com.leuan.lepton.syspackage.mapping

import com.leuan.lepton.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.syspackage.controller.vo.SysPackageVO
import com.leuan.lepton.syspackage.dal.SysPackage
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class SysPackageMapper {
    abstract fun toEntity(sysPackageVO: SysPackageVO): SysPackage
    abstract fun entityToVO(sysPackage: SysPackage): SysPackageVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(sysPackageVO: SysPackageVO, @MappingTarget sysPackage: SysPackage): SysPackage

    abstract fun saveDtoToEntity(sysPackageSaveDTO: SysPackageSaveDTO): SysPackage
}