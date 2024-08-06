package com.leuan.lepton.framework.user.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.role.dal.Role
import com.leuan.lepton.framework.role.mapping.RoleMapper
import com.leuan.lepton.framework.user.controller.dto.UserSaveDTO
import com.leuan.lepton.framework.user.controller.vo.UserInfoVO
import com.leuan.lepton.framework.user.controller.vo.UserVO
import com.leuan.lepton.framework.user.dal.User
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class, RoleMapper::class]
)
abstract class UserMapper {
    abstract fun toEntity(userVO: UserVO): User

    @Mapping(source = "roles", target = "roleIds", qualifiedByName = ["rolesToRoleIds"])
    abstract fun toVO(user: User): UserVO

    @Mapping(source = "roleIds", target = "roles")
    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: UserSaveDTO, @MappingTarget user: User): User
    fun idToEntity(id: Long?): User? {
        return id?.let { User().apply { this.id = it } }
    }

    @Mappings(
        Mapping(target = "roles", expression = "java(rolesToRoleCodes(user.getRoles()))"),
        Mapping(target = "permissions", expression = "java(rolesToPermissionCodes(user.getRoles()))")
    )
    abstract fun toDto(user: User): UserInfoVO


    @Named("rolesToRoleIds")
    fun rolesToRoleIds(roles: Set<Role>): MutableSet<Long> {
        return roles.filter { role -> role.tenantId == getThreadContext().tenantId }.map { it.id }.toMutableSet()
    }

    fun rolesToRoleCodes(roles: Set<Role>): MutableSet<String> {
        return roles.filter { role -> role.tenantId == getThreadContext().tenantId }.map { it.code }.toMutableSet()
    }

    fun rolesToPermissionCodes(roles: Set<Role>): MutableSet<String> {
        return roles.filter { role -> role.tenantId == getThreadContext().tenantId }
            .flatMap { it.menus.map { menu -> menu.permission } }.filter { it.isNotBlank() }.toMutableSet()
    }
}