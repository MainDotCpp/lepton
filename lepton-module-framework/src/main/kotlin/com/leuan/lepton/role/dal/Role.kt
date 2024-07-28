package com.leuan.lepton.role.dal

import com.leuan.lepton.common.dal.BaseAuditEntity
import com.leuan.lepton.menu.dal.Menu
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import org.hibernate.proxy.HibernateProxy

@Comment("系统角色角色")
@Entity
@Table(name = "sys_role")
class Role : BaseAuditEntity() {
    @Comment("角色名称")
    @Column(name = "name", nullable = false)
    @ColumnDefault("''")
    var name: String = ""

    @Comment("角色编码")
    @Column(name = "code", nullable = false)
    @ColumnDefault("''")
    var code: String = ""

    @Comment("是否内置")
    @Column(name = "builtin", nullable = false)
    @ColumnDefault("false")
    var builtin: Boolean = false

    @ManyToMany
    @JoinTable(
        name = "sys_role_menu",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "menu_id")]
    )
    var menus: MutableSet<Menu> = mutableSetOf()


    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Role

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

}