package com.leuan.lepton.common.dal

import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.thread.getThreadContext
import com.leuan.lepton.user.dal.User
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