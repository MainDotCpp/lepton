package com.leuan.lepton.tenant.dal

import com.leuan.lepton.syspackage.dal.SysPackage
import com.leuan.lepton.tenant.enums.TenantTypeEnum
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

    @Comment("租户名称")
    @ColumnDefault("''")
    @Column(name = "name", nullable = false)
    var name: String = ""

    @Comment("租户类型")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    @Column(name = "type", nullable = false)
    var type: TenantTypeEnum = TenantTypeEnum.ACTIVE

    @ManyToOne
    @JoinColumn(name = "sys_package_id")
    var sysPackage: SysPackage? = null
}