package com.leuan.lepton.role.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.role.dal.Role }
 */
@NoArgsConstructor
data class RoleVO(
    var id: Long
) : Serializable