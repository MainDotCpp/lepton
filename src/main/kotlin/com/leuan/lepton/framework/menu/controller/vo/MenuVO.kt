package com.leuan.lepton.framework.menu.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.menu.dal.Menu }
 */
@NoArgsConstructor
data class MenuVO(
    var id: Long
) : Serializable