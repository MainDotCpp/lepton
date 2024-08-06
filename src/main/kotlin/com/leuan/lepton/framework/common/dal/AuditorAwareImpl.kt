package com.leuan.lepton.framework.common.dal

import com.leuan.lepton.framework.common.log.logInfo
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.user.dal.User
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuditorAwareImpl : AuditorAware<User> {
    override fun getCurrentAuditor(): Optional<User> {
        logInfo("AuditorAwareImpl.getCurrentAuditor")
        val userId = getThreadContext().userId
        return Optional.of(User().apply { id = userId })
    }
}