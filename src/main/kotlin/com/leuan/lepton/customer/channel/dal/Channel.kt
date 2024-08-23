package com.leuan.lepton.customer.channel.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Comment("客资渠道")
@Entity
@Table(name = "c_channel")
class Channel : BaseAuditEntity() {

    @Comment("渠道名称")
    @Column(name = "name", nullable = false)
    var name: String = ""

    @Comment("图标")
    @Column(name = "icon")
    var icon: String? = null

    @Comment("文本颜色")
    @Column(name = "text_color")
    var textColor: String? = null

    @Comment("背景颜色")
    @Column(name = "background_color")
    var backgroundColor: String? = null

}