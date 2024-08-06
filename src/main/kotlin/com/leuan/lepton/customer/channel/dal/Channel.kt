package com.leuan.lepton.customer.channel.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Comment("客资渠道")
@Entity
@Table(name = "c_channel")
class Channel : BaseAuditEntity()