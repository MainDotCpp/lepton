package com.leuan.lepton.framework.dept.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.dept.dal.Dept }
 */
@NoArgsConstructor
data class DeptVO(
    var id: Long, var name: String = "", var code: String = "", var parentId: Long = 0L, var sort: Int = 0
) : Serializable