package com.leuan.lepton.framework.user.dal

import com.leuan.lepton.framework.role.dal.Role
import com.leuan.lepton.framework.tenant.dal.Tenant
import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.Comment

@Comment("系统用户")
@Entity
@Table(name = "sys_user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @Comment("姓名")
    @Column(name = "name")
    var name: String = ""

    @Comment("手机号")
    @Column(name = "phone", unique = true, nullable = false)
    var phone: String = ""

    @Comment("密码")
    @Column(name = "password", nullable = false)
    var password: String = ""

    @Comment("头像")
    @Column(name = "avatar")
    var avatar: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sys_user_tenant",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "tenant_id")],
    )
    var tenants: MutableSet<Tenant> = mutableSetOf()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sys_user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()
}