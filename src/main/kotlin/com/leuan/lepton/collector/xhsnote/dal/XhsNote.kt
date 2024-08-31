package com.leuan.lepton.collector.xhsnote.dal

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "xhs_note")
class XhsNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Size(max = 64)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 64)
    var userId: String? = null

    @Size(max = 64)
    @Column(name = "nickname", length = 64)
    var nickname: String? = null

    @Size(max = 255)
    @Column(name = "avatar")
    var avatar: String? = null

    @Size(max = 255)
    @Column(name = "ip_location")
    var ipLocation: String? = null

    @NotNull
    @Column(name = "add_ts", nullable = false)
    var addTs: Long? = null

    @NotNull
    @Column(name = "last_modify_ts", nullable = false)
    var lastModifyTs: Long? = null

    @Size(max = 64)
    @NotNull
    @Column(name = "note_id", nullable = false, length = 64)
    var noteId: String? = null

    @Size(max = 16)
    @Column(name = "type", length = 16)
    var type: String? = null

    @Size(max = 255)
    @Column(name = "title")
    var title: String? = null

    @Lob
    @Column(name = "`desc`")
    var desc: String? = null

    @Lob
    @Column(name = "video_url")
    var videoUrl: String? = null

    @NotNull
    @Column(name = "time", nullable = false)
    var time: Long? = null

    @NotNull
    @Column(name = "last_update_time", nullable = false)
    var lastUpdateTime: Long? = null

    @Size(max = 16)
    @Column(name = "liked_count", length = 16)
    var likedCount: String? = null

    @Size(max = 16)
    @Column(name = "collected_count", length = 16)
    var collectedCount: String? = null

    @Size(max = 16)
    @Column(name = "comment_count", length = 16)
    var commentCount: String? = null

    @Size(max = 16)
    @Column(name = "share_count", length = 16)
    var shareCount: String? = null

    @Lob
    @Column(name = "image_list")
    var imageList: String? = null

    @Lob
    @Column(name = "tag_list")
    var tagList: String? = null

    @Size(max = 255)
    @Column(name = "note_url")
    var noteUrl: String? = null
}