package com.leuan.lepton.framework.common.thread

import lombok.NoArgsConstructor


@NoArgsConstructor
data class ThreadContext(
    var tenantId: Long = -1L,
    var userId: Long,
    var username: String,
    var token: String,
    var ignoreTenantId: Boolean = false
)

val threadLocal = ThreadLocal<ThreadContext>()

fun getThreadContext(): ThreadContext {
    return threadLocal.get() ?: ThreadContext(-1, -1, "", "", true)
}

fun setThreadContext(threadContext: ThreadContext) {
    threadLocal.set(threadContext)
}

fun clearThreadContext() {
    threadLocal.remove()
}

fun <T> ignoreTenantId(block: () -> T): T {
    val threadContext = getThreadContext()
    threadContext.ignoreTenantId = true
    val result = block()
    threadContext.ignoreTenantId = false
    return result
}
