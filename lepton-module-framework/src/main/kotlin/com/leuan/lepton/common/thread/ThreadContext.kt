package com.leuan.lepton.common.thread

import com.leuan.lepton.common.annotations.NoArgs
import com.leuan.lepton.common.log.logInfo


@NoArgs
data class ThreadContext(
    var tenantId: Long = -1L,
    var userId: Long,
    var username: String,
    var token: String
)

val threadLocal = ThreadLocal<ThreadContext>()

fun getThreadContext(): ThreadContext {
    return threadLocal.get()
}

fun setThreadContext(threadContext: ThreadContext) {
    threadLocal.logInfo("=================初始化线程上下文=================")
    threadLocal.set(threadContext)
}

fun clearThreadContext() {
    threadLocal.remove()
    threadLocal.logInfo("=================清除线程上下文=================")
}
