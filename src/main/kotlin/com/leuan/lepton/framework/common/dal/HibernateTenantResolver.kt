package com.leuan.lepton.framework.common.dal

import com.leuan.lepton.framework.common.log.logDebug
import com.leuan.lepton.framework.common.thread.getThreadContext
import org.hibernate.cfg.AvailableSettings
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.stereotype.Component

@Component
class HibernateTenantResolver : CurrentTenantIdentifierResolver<Long>, HibernatePropertiesCustomizer {

    override fun isRoot(tenantId: Long?): Boolean {
        logDebug("是否忽略租户: ${getThreadContext().ignoreTenantId}")
        return getThreadContext().ignoreTenantId
    }

    override fun resolveCurrentTenantIdentifier(): Long {
        return getThreadContext().tenantId
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }

    override fun customize(hibernateProperties: MutableMap<String, Any>?) {
        hibernateProperties?.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this)
    }
}