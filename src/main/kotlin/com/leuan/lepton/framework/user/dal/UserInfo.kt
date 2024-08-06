package com.leuan.lepton.framework.user.dal

/**
 * Projection for {@link com.leuan.lepton.framework.user.dal.User}
 */
interface UserInfo {
    val id: Long?
    val name: String?
    val phone: String?
    val password: String?
    val avatar: String?
}
