package com.leuan.lepton.collector.xhsnotecomment.dal

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "xhs_note_comment")
class XhsNoteComment {
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
    @Column(name = "comment_id", nullable = false, length = 64)
    var commentId: String? = null

    @NotNull
    @Column(name = "create_time", nullable = false)
    var createTime: Long? = null

    @Size(max = 64)
    @NotNull
    @Column(name = "note_id", nullable = false, length = 64)
    var noteId: String? = null

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    var content: String? = null

    @NotNull
    @Column(name = "sub_comment_count", nullable = false)
    var subCommentCount: Int? = null

    @Size(max = 512)
    @Column(name = "pictures", length = 512)
    var pictures: String? = null

    @Size(max = 64)
    @Column(name = "parent_comment_id", length = 64)
    var parentCommentId: String? = null
}