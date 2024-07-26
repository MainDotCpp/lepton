package com.leuan.lepton.user.controller.dto

/**
 * DTO for {@link com.leuan.lepton.user.dal.User }
 */
data class UserSaveDTO(
    val id: Long? = null, val name: String? = null, val phone: String? = null, val password: String? = null,
)
