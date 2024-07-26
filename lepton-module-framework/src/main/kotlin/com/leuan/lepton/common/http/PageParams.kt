package com.leuan.lepton.common.http

import com.fasterxml.jackson.annotation.JsonProperty

interface PageParams {
    var current: Long
    var pageSize: Long
}

data class PageDTO<T>(var params: PageParams) {
    var records: Collection<T> = emptyList()
    val current: Long
        get() = params.current

    val pageSize: Long
        get() = params.pageSize
    val pages: Long
        get() = total / pageSize + 1

    val hasNextPage: Boolean
        get() = current < pages

    val hasPreviousPage: Boolean
        get() = current > 1

    val offset: Long
        get() = (current - 1) * pageSize

    var total: Long = 0L


}
