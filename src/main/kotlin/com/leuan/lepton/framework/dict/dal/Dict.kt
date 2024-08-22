package com.leuan.lepton.framework.dict.dal

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Comment("字典")
@Entity
@Table(name = "sys_dict")
class Dict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Comment("字典类型")
    @Column(name = "type", nullable = false)
    @ColumnDefault("''")
    var type: String = ""


    @Comment("字典名称")
    @Column(name = "name", nullable = false)
    @ColumnDefault("''")
    var name: String = ""


    @OneToMany(mappedBy = "dict", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: MutableSet<DictItem> = mutableSetOf()
}