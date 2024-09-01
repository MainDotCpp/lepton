package com.leuan.lepton.framework.config.dal

import com.leuan.lepton.framework.config.enums.ConfigType
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes.JSON

@Entity
@Table(name = "sys_config")
class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    var type: ConfigType =  ConfigType.GLOBAL

    var globalConfig: GlobalConfig? = null

    var tenantConfig:TenantConfig? = null

    var tenantId:Long? = null

}