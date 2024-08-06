package com.leuan.lepton.framework.role.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.menu.mapping.MenuMapper
import com.leuan.lepton.framework.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.framework.role.controller.vo.RoleVO
import com.leuan.lepton.framework.role.dal.Role
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class, MenuMapper::class]
)
abstract class RoleMapper {
    @org.mapstruct.Mapping(source = "createdByName", target = "createdBy.name")
    abstract fun toEntity(roleVO: RoleVO): Role

    @Mapping(source = "menus", target = "menuIds")
    @org.mapstruct.Mapping(source = "createdBy.name", target = "createdByName")
    abstract fun toVO(role: Role): RoleVO

    @Mapping(source = "menuIds", target = "menus")
    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: RoleSaveDTO, @MappingTarget role: Role): Role


    fun idToEntity(id: Long?): Role? {
        return id?.let { Role().apply { this.id = it } }
    }

    fun entityToId(role: Role?): Long? {
        return role?.id
    }

}