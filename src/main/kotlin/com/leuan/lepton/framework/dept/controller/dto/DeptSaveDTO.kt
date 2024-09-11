package com.leuan.lepton.framework.dept.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.framework.dept.dal.Dept }
 */
@NoArgsConstructor
data class DeptSaveDTO(
    val id: Long? = null, val name: String = "", val code: String = "", val parentId: Long = 0L, val sort: Int = 0
)
