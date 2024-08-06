package com.leuan.lepton.framework.menu.dal

import com.leuan.lepton.framework.menu.enums.MenuTypeEnum
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.proxy.HibernateProxy

@Comment("菜单")
@Entity
@Table(name = "sys_menu")
class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Comment("菜单名称")
    @ColumnDefault("''")
    @Column(name = "name", nullable = false)
    var name: String = ""

    @Comment("上级菜单")
    @ColumnDefault("0")
    @Column(name = "parent_id", nullable = false)
    var parentId: Long = 0

    @ColumnDefault("'CATALOG'")
    @Enumerated(EnumType.STRING)
    @Comment("菜单类型")
    @Column(name = "type", nullable = false)
    var type: MenuTypeEnum = MenuTypeEnum.CATALOG

    @Comment("菜单路径")
    @ColumnDefault("''")
    @Column(name = "path", nullable = false)
    var path: String = ""

    @Comment("菜单图标")
    @ColumnDefault("''")
    @Column(name = "icon", nullable = false)
    var icon: String = ""

    @Comment("排序")
    @ColumnDefault("0")
    @Column(name = "sort", nullable = false)
    var sort: Int = 0

    @Comment("是否隐藏")
    @ColumnDefault("false")
    @Column(name = "hidden", nullable = false)
    var hidden: Boolean = false

    @Comment("是否缓存")
    @ColumnDefault("false")
    @Column(name = "cache", nullable = false)
    var cache: Boolean = false

    @Comment("组件路径")
    @ColumnDefault("''")
    @Column(name = "component", nullable = false)
    var component: String = ""

    @Comment("权限标识")
    @ColumnDefault("''")
    @Column(name = "permission", nullable = false)
    var permission: String = ""
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Menu

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

}