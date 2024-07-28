package com.leuan.lepton.common.dal

import com.leuan.lepton.common.thread.ThreadContext
import com.leuan.lepton.common.thread.getThreadContext
import jakarta.persistence.PrePersist
import jakarta.persistence.PreRemove
import jakarta.persistence.PreUpdate
import org.hibernate.event.spi.*


class TenantEventListener {
    @PreUpdate
    @PreRemove
    @PrePersist
    fun setTenant(entity: BaseAuditEntity) {
        val ctx = getThreadContext()
        // 判断忽略租户条件
        entity.tenantId = ctx.tenantId
    }
}