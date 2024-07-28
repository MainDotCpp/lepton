package com.leuan.lepton.user.controller.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.leuan.lepton.common.annotations.AllOpen
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@AllOpen
@NoArgsConstructor
data class UserInfoVO(
    var id: Long,
    var name: String,
    var phone: String,
    var tenants: MutableSet<Long> = mutableSetOf(),
    var roles: MutableSet<String> = mutableSetOf(),
    var permissions: MutableSet<String> = mutableSetOf()
) : UserDetails {

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return permissions.map { GrantedAuthority { it } }.toMutableList()
    }

    @JsonIgnore
    override fun getPassword(): String {
        return ""
    }

    @JsonIgnore
    override fun getUsername(): String {
        return id.toString()
    }
}