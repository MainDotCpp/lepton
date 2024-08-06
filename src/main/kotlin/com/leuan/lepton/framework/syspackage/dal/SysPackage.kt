package com.leuan.lepton.framework.syspackage.dal

import com.leuan.lepton.framework.menu.dal.Menu
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Comment("套餐")
@Entity
@Table(name = "sys_package")
class SysPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Comment("套餐名称")
    @Column(name = "name", nullable = false)
    @ColumnDefault("''")
    var name: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "sys_package_menu",
        joinColumns = [JoinColumn(name = "sysPackage_id")],
        inverseJoinColumns = [JoinColumn(name = "menu_id")]
    )
    var menus: MutableSet<Menu> = mutableSetOf()
}