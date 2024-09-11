package com.leuan.lepton.framework.common.dal

import com.leuan.lepton.framework.common.thread.getThreadContext
import jakarta.persistence.PrePersist
import jakarta.persistence.PreRemove
import jakarta.persistence.PreUpdate


class TenantEventListener {
    @PreUpdate
    @PreRemove
    @PrePersist
    fun setTenant(entity: BaseAuditEntity) {
        if (getThreadContext().ignoreTenantId) return
        entity.tenantId = getThreadContext().tenantId
        entity.permissionKey = getThreadContext().deptCode
    }
}