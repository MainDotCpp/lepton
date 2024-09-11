package com.leuan.lepton.framework.dept.dal

import com.leuan.lepton.framework.common.dal.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Comment("部门")
@Entity
@Table(name = "sys_dept")
class Dept : BaseAuditEntity(){

    @Comment("部门名称")
    @Column(name = "name", nullable = false, length = 50)
    var name: String = "";

    @Comment("部门编码")
    @Column(name = "code", nullable = false, length = 50)
    var code: String = "";

    @Comment("父部门ID")
    @Column(name = "parent_id", nullable = false)
    var parentId: Long = 0L;

    @Comment("排序")
    @Column(name = "sort", nullable = false)
    var sort: Int = 0;

}