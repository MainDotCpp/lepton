package com.leuan.lepton.framework.user.dal

import com.leuan.lepton.framework.tenant.dal.Tenant
import com.leuan.lepton.framework.user.enums.DataPermissionType
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Table(name = "sys_user_tenant")
class SysUserTenant {
    @EmbeddedId
    var id: SysUserTenantId? = null

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @MapsId("tenantId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    var tenant: Tenant? = null

    @ColumnDefault("'SELF'")
    @Column(name = "data_permission", nullable = false)
    @Enumerated(EnumType.STRING)
    var dataPermission: DataPermissionType = DataPermissionType.SELF

    @Comment("部门ID")
    @Column(name = "dept_id")
    var deptId: Long? = null
}