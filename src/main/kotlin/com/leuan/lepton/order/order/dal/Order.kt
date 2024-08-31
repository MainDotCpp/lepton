package com.leuan.lepton.order.order.dal

import com.leuan.lepton.customer.customer.dal.Customer
import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import com.leuan.lepton.framework.user.dal.User
import com.leuan.lepton.order.goods.dal.Goods
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Comment("订单")
@Table(name = "o_order")
class Order : BaseAuditEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    var goods: Goods = Goods()

    @Comment("支付金额")
    @Column(name = "pay_amount", nullable = false)
    @ColumnDefault("0")
    var payAmount: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    var seller: User = User()
}