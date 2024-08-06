package com.leuan.lepton.customer.customer.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Comment("客资")
@Entity
@Table(name = "c_customer")
class Customer : BaseAuditEntity()