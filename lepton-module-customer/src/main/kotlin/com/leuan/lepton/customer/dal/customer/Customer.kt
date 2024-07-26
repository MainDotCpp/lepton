package com.leuan.lepton.customer.dal.customer

import com.leuan.lepton.common.dal.BaseAuditEntity
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Comment("客资")
@Table(name = "c_customer")
open class Customer : BaseAuditEntity() {

    @Comment("姓名")
    @ColumnDefault("''")
    @Column(name = "name", nullable = false)
    open var name: String = ""

    @Comment("电话")
    @Column(name = "phone")
    open var phone: String? = null

    @Comment("微信")
    @Column(name = "wx")
    open var wx: String? = null

}