package com.leuan.lepton.role.mapping

import com.leuan.lepton.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.role.controller.vo.RoleVO
import com.leuan.lepton.role.dal.Role
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class RoleMapper {
    abstract fun toEntity(roleVO: RoleVO): Role
    abstract fun entityToVO(role: Role): RoleVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(roleVO: RoleVO, @MappingTarget role: Role): Role

    abstract fun saveDtoToEntity(roleSaveDTO: RoleSaveDTO): Role
}