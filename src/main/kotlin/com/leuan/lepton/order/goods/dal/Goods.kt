package com.leuan.lepton.order.goods.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Comment("商品")
@Table(name = "o_goods")
class Goods : BaseAuditEntity() {
    @Comment("商品名称")
    @Column(name = "name", nullable = false)
    var name: String = ""

    @ColumnDefault("0")
    @Comment("商品价格")
    @Column(name = "price", nullable = false)
    var price: Long = 0

    @Comment("商品状态")
    @Column(name = "status", nullable = false)
    var status: Int = 1
}