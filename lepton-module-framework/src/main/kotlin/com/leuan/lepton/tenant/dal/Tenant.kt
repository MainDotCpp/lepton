package com.leuan.lepton.tenant.dal

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Table(name = "sys_tenant")
open class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Comment("租户名称")
    @ColumnDefault("''")
    @Column(name = "name", nullable = false)
    open var name: String = ""
}