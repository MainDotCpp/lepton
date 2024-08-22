package com.leuan.lepton.framework.dict.dal

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment

@Entity
@Table(name = "sys_dict_item")
class DictItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Comment("字典项")
    @Column(name = "item", nullable = false)
    @ColumnDefault("''")
    var item: String = ""

    @Comment("字典值")
    @Column(name = "value", nullable = false)
    @ColumnDefault("''")
    var value: String = ""


    @Comment("排序")
    @Column(name = "sort", nullable = false)
    @ColumnDefault("0")
    var sort: Int = 0

    @Comment("是否启用")
    @Column(name = "enabled", nullable = false)
    @ColumnDefault("true")
    var enabled: Boolean = true

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dict_id")
    var dict: Dict? = null
}