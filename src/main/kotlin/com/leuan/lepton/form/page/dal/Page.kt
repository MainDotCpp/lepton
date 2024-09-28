package com.leuan.lepton.form.page.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import com.leuan.lepton.form.component.dal.Component
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Comment("表单页面")
@Entity
@Table(name = "f_page")
class Page : BaseAuditEntity() {

    @Comment("页面名称")
    @Column(name = "name", nullable = false, length = 50)
    @ColumnDefault("''")
    var name: String = ""


    @OneToMany(mappedBy = "page", cascade = [CascadeType.ALL], orphanRemoval = true)
    var components: MutableSet<Component> = mutableSetOf()
}