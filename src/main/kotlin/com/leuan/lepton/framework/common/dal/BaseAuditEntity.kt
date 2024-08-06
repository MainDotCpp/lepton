package com.leuan.lepton.framework.common.dal

import com.leuan.lepton.framework.user.dal.User
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.TenantId
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*


@EntityListeners(AuditingEntityListener::class, TenantEventListener::class)
@NoArgsConstructor
@MappedSuperclass
class BaseAuditEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,

    @Comment("创建人")
    @CreatedBy
    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    var createdBy: User? = null,

    @Comment("创建时间")
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    var createdAt: Date = Date(),

    @Comment("更新时间")
    @LastModifiedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Date = Date(),

    @Comment("更新人")
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    var updatedBy: User? = null,

    @Comment("租户ID")
    @ColumnDefault("0")
    @Column(name = "tenant_id")
    @TenantId
    var tenantId: Long = 0,
) {

}