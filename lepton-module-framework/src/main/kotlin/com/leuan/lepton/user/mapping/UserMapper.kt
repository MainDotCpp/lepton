package com.leuan.lepton.user.mapping

import com.leuan.lepton.user.controller.dto.UserSaveDTO
import com.leuan.lepton.user.controller.vo.UserVO
import com.leuan.lepton.user.dal.User
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class UserMapper {
    abstract fun toEntity(userVO: UserVO): User
    abstract fun entityToVO(user: User): UserVO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(userVO: UserVO, @MappingTarget user: User): User

    abstract fun saveDtoToEntity(userSaveDTO: UserSaveDTO): User
}