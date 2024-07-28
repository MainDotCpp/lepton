package com.leuan.lepton.common.dal

import com.leuan.lepton.common.thread.getThreadContext
import org.hibernate.annotations.Comment
import org.hibernate.boot.beanvalidation.HibernateTraversableResolver
import org.hibernate.cfg.AvailableSettings
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.stereotype.Component

@Component
class PartitionedTenantIdentifierResolver : CurrentTenantIdentifierResolver<Long>, HibernatePropertiesCustomizer {
    override fun resolveCurrentTenantIdentifier(): Long {
        return getThreadContext().tenantId
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }

    override fun customize(hibernateProperties: MutableMap<String, Any>?) {
        hibernateProperties?.set(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this)
    }
}