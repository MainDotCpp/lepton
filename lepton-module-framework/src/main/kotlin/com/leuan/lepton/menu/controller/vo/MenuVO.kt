package com.leuan.lepton.menu.controller.vo

import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.menu.dal.Menu }
 */
data class `MenuVO`(
    var id: Long? = null,
    var name: String = ""
) : Serializable