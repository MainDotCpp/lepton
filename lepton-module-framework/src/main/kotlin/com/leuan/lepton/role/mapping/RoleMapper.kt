package com.leuan.lepton.role.mapping

import com.leuan.lepton.common.mapping.LeptonBaseMapping
import com.leuan.lepton.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.role.controller.vo.RoleVO
import com.leuan.lepton.role.dal.Role
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class RoleMapper {
    abstract fun toEntity(roleVO: RoleVO): Role
    abstract fun toVO(role: Role): RoleVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: RoleSaveDTO, @MappingTarget role: Role): Role

    fun idToEntity(id: Long?): Role? {
        return id?.let { Role().apply { this.id = it } }
    }

}