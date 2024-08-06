package com.leuan.lepton.framework.common.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.leuan.lepton.framework.common.annotations.AllOpen
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllOpen
@NoArgsConstructor
data class BaseQueryDTO(
    var current: Long = 1L,
    var pageSize: Long = 10L,
    var sorter: List<String>? = null,
)

@AllArgsConstructor
class PageDTO<T>(
    params: BaseQueryDTO
) {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var params: BaseQueryDTO = params

    var data: Collection<T> = emptyList()
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
