package com.leuan.lepton.common.dal

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import java.util.Date


@MappedSuperclass
open class BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long = 0

    @Comment("创建人")
    @ColumnDefault("0")
    @Column(name = "created_by")
    open var createdBy: Long = 0

    @Comment("创建时间")
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    open var createdAt: Date = Date()

    @Comment("更新时间")
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    open var updatedAt: Date = Date()

    @Comment("更新人")
    @ColumnDefault("0")
    @Column(name = "updated_by")
    open var updatedBy: Long = 0

    @Comment("租户ID")
    @ColumnDefault("0")
    @Column(name = "tenant_id")
    open var tenantId: Long = 0
}