package com.leuan.lepton.framework.tenant.dal

import com.fasterxml.jackson.annotation.JsonBackReference
import com.leuan.lepton.framework.syspackage.dal.SysPackage
import com.leuan.lepton.framework.tenant.enums.TenantTypeEnum
import com.leuan.lepton.framework.user.dal.User
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Table(name = "sys_tenant")
class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Comment("logo")
    @ColumnDefault("''")
    @Column(name = "logo", nullable = false)
    var logo: String = ""

    @Comment("租户名称")
    @ColumnDefault("''")
    @Column(name = "name", nullable = false)
    var name: String = ""

    @Comment("租户类型")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    @Column(name = "type", nullable = false)
    var type: TenantTypeEnum = TenantTypeEnum.ACTIVE

    @Comment("企业识别码")
    @ColumnDefault("''")
    @Column(name = "code", nullable = false, unique = true)
    var code: String = ""

    @ManyToOne
    @JoinColumn(name = "sys_package_id")
    var sysPackage: SysPackage? = null

    @JsonBackReference
    @ManyToMany(mappedBy = "tenants")
    var users: MutableSet<User> = mutableSetOf()


}