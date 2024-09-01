package com.leuan.lepton.customer.brand.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Comment("品牌")
@Table(name = "c_brand")
class Brand : BaseAuditEntity(){

    @Comment("品牌名称")
    @Column(name = "name", nullable = false)
    var name: String = ""

}