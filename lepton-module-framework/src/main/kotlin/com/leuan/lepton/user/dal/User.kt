package com.leuan.lepton.user.dal

import com.leuan.lepton.common.dal.BaseAuditEntity
import com.leuan.lepton.tenant.dal.Tenant
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Comment("系统用户")
@Entity
@Table(name = "sys_user")
class User : BaseAuditEntity() {
    @Comment("姓名")
    @Column(name = "name")
    var name: String? = null

    @Comment("手机号")
    @Column(name = "phone", unique = true, nullable = false)
    var phone: String? = null

    @Comment("密码")
    @Column(name = "password", nullable = false)
    var password: String? = null

    @ManyToMany
    @JoinTable(
        name = "sys_user_tenant",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "tenant_id")]
    )
    var tenants: MutableSet<Tenant> = mutableSetOf()
}