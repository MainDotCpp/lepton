package com.leuan.lepton.framework.common.dal.annotations

import kotlin.reflect.KClass

enum class QueryMethod {
    EQ, NE, IN, NOT_IN, BETWEEN, NOT_BETWEEN
}


@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
)
@Retention(AnnotationRetention.RUNTIME)
annotation class QueryField(
    val fieldName: String = "",
    val method: QueryMethod = QueryMethod.IN,
    val type: KClass<out Comparable<*>> = Long::class
)
