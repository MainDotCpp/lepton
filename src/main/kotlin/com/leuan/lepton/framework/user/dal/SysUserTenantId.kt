package com.leuan.lepton.framework.user.dal

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotNull
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.*

@Embeddable
open class SysUserTenantId : Serializable {
    @NotNull
    @Column(name = "user_id", nullable = false)
    open var userId: Long? = null

    @NotNull
    @Column(name = "tenant_id", nullable = false)
    open var tenantId: Long? = null
    override fun hashCode(): Int = Objects.hash(userId, tenantId)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as SysUserTenantId

        return userId == other.userId &&
                tenantId == other.tenantId
    }

    companion object {
        private const val serialVersionUID = -4239470803944035487L
    }
}