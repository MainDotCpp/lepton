package com.leuan.lepton.framework.common.log


import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T : Any> T.logger(): Logger {
    return LoggerFactory.getLogger(this.javaClass)
}

fun <T : Any> T.logError(e: Throwable) {
    logger().error("", e)
}

fun <T : Any> T.logError(msg: String? = null) {
    logger().error(msg)
}

fun <T : Any> T.logInfo(msg: String? = null) {
    logger().info(msg)
}

fun <T : Any> T.logDebug(msg: String? = null) {
    logger().debug(msg)
}
