package com.leuan.lepton.common.thread

import com.leuan.lepton.common.annotations.NoArgs
import com.leuan.lepton.common.log.logInfo
import kotlin.concurrent.getOrSet


@NoArgs
data class ThreadContext(
    var tenantId: Long = -1L,
    var userId: Long,
    var username: String,
    var token: String
)

val threadLocal = ThreadLocal<ThreadContext>()

fun getThreadContext(): ThreadContext {
    return threadLocal.get() ?: ThreadContext(-1, -1, "", "")
}

fun setThreadContext(threadContext: ThreadContext) {
    threadLocal.set(threadContext)
}

fun clearThreadContext() {
    threadLocal.remove()
}
