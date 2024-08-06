package com.leuan.lepton.framework.common.mapping

import com.leuan.lepton.framework.user.dal.User
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class LeptonBaseMapping {

    fun userToUserId(user: User?): Long? {
        return user?.id
    }

    fun userIdToUser(userId: Long?): User? {
        if (userId == null || userId == 0L) return null
        return User().apply { id = userId }
    }

}