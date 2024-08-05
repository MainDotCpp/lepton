package com.leuan.lepton.user.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.user.dal.User }
 */
@NoArgsConstructor
data class UserSaveDTO(
    val id: Long? = null,
    val name: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val avatar: String = "",
    val roleIds: MutableSet<Long> = mutableSetOf()
)
