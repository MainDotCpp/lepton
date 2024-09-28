package com.leuan.lepton.form.component.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import com.leuan.lepton.form.component.enums.ComponentType
import com.leuan.lepton.form.page.dal.Page
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "f_component")
class Component : BaseAuditEntity() {

    @Comment("组件类型")
    @Column(name = "type", nullable = false, length = 50)
    @ColumnDefault("'BUTTON'")
    var type: ComponentType = ComponentType.BUTTON

    @JdbcTypeCode(SqlTypes.JSON)
    var metadata: HashMap<String, Any?> = HashMap()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    var page: Page? = null
}