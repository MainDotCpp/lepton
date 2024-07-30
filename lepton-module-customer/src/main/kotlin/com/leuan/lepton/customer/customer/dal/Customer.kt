package com.leuan.lepton.customer.customer.dal

import com.leuan.lepton.common.dal.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Comment("客资")
@Entity
@Table(name = "customer")
class Customer(

    @Comment("姓名")
    @Column(name = "name", nullable = false)
    @ColumnDefault("''")
    var name: String,

    @Comment("手机号")
    @Column(name = "phone")
    var phone: String?,

    @Comment("微信号")
    @Column(name = "wx")
    var wx: String?,

    ) : BaseAuditEntity()