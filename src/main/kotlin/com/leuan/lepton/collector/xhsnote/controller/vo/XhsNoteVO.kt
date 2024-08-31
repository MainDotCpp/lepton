package com.leuan.lepton.collector.xhsnote.controller.vo

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.collector.xhsnote.dal.XhsNote }
 */
@NoArgsConstructor
data class XhsNoteVO(
    var id: Long,
    @field:NotNull @field:Size(max = 64) var userId: String? = null,
    @field:Size(max = 64) var nickname: String? = null,
    @field:Size(max = 255) var avatar: String? = null,
    @field:Size(max = 255) var ipLocation: String? = null,
    @field:NotNull var addTs: Long? = null,
    @field:NotNull var lastModifyTs: Long? = null,
    @field:NotNull @field:Size(max = 64) var noteId: String? = null,
    @field:Size(max = 16) var type: String? = null,
    @field:Size(max = 255) var title: String? = null,
    var desc: String? = null,
    var videoUrl: String? = null,
    @field:NotNull var time: Long? = null,
    @field:NotNull var lastUpdateTime: Long? = null,
    @field:Size(max = 16) var likedCount: String? = null,
    @field:Size(max = 16) var collectedCount: String? = null,
    @field:Size(max = 16) var commentCount: String? = null,
    @field:Size(max = 16) var shareCount: String? = null,
    var imageList: String? = null,
    var tagList: String? = null,
    @field:Size(max = 255) var noteUrl: String? = null
) : Serializable