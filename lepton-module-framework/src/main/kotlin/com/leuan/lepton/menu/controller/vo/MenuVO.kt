package com.leuan.lepton.menu.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.menu.dal.Menu }
 */
@NoArgsConstructor
data class MenuVO(
    var id: Long
) : Serializable