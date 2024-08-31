package com.leuan.lepton.framework.common.utils

import com.leuan.lepton.framework.common.dal.annotations.QueryField
import com.leuan.lepton.framework.common.dal.annotations.QueryMethod
import com.leuan.lepton.framework.common.http.BaseQueryDTO
import com.leuan.lepton.framework.common.log.logDebug
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQuery
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotations


inline fun <T, Q : BaseQueryDTO> JPAQuery<T>.buildExpressions(
    queryDTO: Q,
    sort: Boolean = true,
    where: (queryDTO: Q) -> Array<BooleanExpression?>? = { arrayOf() }
): JPAQuery<T> {
    this.where(*where(queryDTO)!!)

    // 获取queryDTO 所有的字段
    val fields = queryDTO.javaClass.kotlin.declaredMemberProperties
    // 构造查询条件
    fields.forEach { field ->
        val queryAnno = field.findAnnotations(QueryField::class).firstOrNull() ?: return@forEach
        logDebug("queryAnno: ${field.name}")
        val value = field.getValue(queryDTO, field) as Collection<Comparable<*>>? ?: return@forEach
        if (value.isEmpty()) return@forEach
        val path = Expressions.comparablePath(queryAnno.type.java, queryAnno.fieldName)
        when (queryAnno.method) {
            QueryMethod.NOT_IN -> this.where(path.notIn(value))
            QueryMethod.EQ -> this.where(path.eq(value.first()))
            QueryMethod.NE -> this.where(path.ne(value.first()))
            QueryMethod.BETWEEN -> this.where(path.between(value.first(), value.last()))
            QueryMethod.NOT_BETWEEN -> this.where(path.notBetween(value.first(), value.last()))
            QueryMethod.IN -> this.where(path.`in`(value))
        }


    }


    // 构造排序条件
    if (sort) {
        logDebug("sort: ${queryDTO.sorter}")
        queryDTO.sorter?.let { sorter ->
            sorter.forEach { it ->
                val (key, value) = it.split("_")
                if (value == "ascend") {
                    this.orderBy(OrderSpecifier(Order.ASC, Expressions.path(Long::class.java, key)))
                } else {
                    this.orderBy(OrderSpecifier(Order.DESC, Expressions.path(Long::class.java, key)))
                }
            }
        }
        this.orderBy(OrderSpecifier(Order.DESC, Expressions.path(Long::class.java, "id")))
    }
    return this
}
