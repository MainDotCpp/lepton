package com.leuan.lepton.customer.customer.dal

import com.leuan.lepton.customer.channel.dal.Channel
import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import com.leuan.lepton.framework.user.dal.User
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Comment("客资")
@Entity
@Table(name = "c_customer")
class Customer : BaseAuditEntity() {

    @Comment("姓名")
    @Schema(description = "姓名", nullable = false)
    @Column(name = "name", nullable = false)
    @ColumnDefault("''")
    var name: String = ""

    @Comment("手机号")
    @Schema(description = "手机号", nullable = false)
    @Column(name = "phone")
    var phone: String? = null

    @Comment("微信号")
    @Schema(description = "微信号", nullable = false)
    @Column(name = "wechat")
    var wechat: String? = null

    @Comment("渠道")
    @ManyToOne
    @JoinColumn(name = "channel_id")
    var channel: Channel = Channel()

    @Comment("拍摄类型")
    @Schema(description = "拍摄类型", nullable = false)
    @Column(name = "photo_type", nullable = false)
    @ColumnDefault("''")
    var photoType: String = ""

    @Comment("来源")
    @Schema(description = "来源", nullable = false)
    @Column(name = "source", nullable = false)
    @ColumnDefault("''")
    var source: String = ""

    @Comment("跟进状态")
    @Schema(description = "跟进状态")
    @Column(name = "follow_status")
    var followStatus: String? = null

    @Comment("备注")
    @Column(name = "remark")
    var remark: String? = null

    @Comment("销售")
    @ManyToOne
    @JoinColumn(name = "sales_id")
    var sale: User = User()
}