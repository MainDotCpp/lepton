package com.leuan.lepton.framework.user.controller.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.leuan.lepton.framework.common.annotations.AllOpen
import com.leuan.lepton.framework.user.enums.DataPermissionType
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable


@AllOpen
@NoArgsConstructor
data class UserInfoVO(
    var id: Long,
    var name: String,
    var phone: String,
    var roles: MutableSet<String> = mutableSetOf(),
    var permissions: MutableSet<String> = mutableSetOf(),
    var tenants: MutableSet<TenantDto> = mutableSetOf(),
    var avatar: String?,
    var deptCode: String?,
    var dataPermission: DataPermissionType? = DataPermissionType.SELF
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

    /**
     * DTO for {@link com.leuan.lepton.framework.tenant.dal.Tenant}
     */
    @NoArgsConstructor
    data class TenantDto(var id: Long, var name: String = "", var logo: String = "") : Serializable
}