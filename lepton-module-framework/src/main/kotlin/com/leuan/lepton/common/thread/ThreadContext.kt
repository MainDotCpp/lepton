package com.leuan.lepton.common.thread

import com.leuan.lepton.common.log.logInfo


data class ThreadContext(
    var tenantId: Long?,
    var userId: Long?,
    var username: String = "",
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
