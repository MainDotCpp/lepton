package com.leuan.lepton.framework.config.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.framework.config.dal.Config }
 */
@NoArgsConstructor
data class ConfigSaveDTO(
    val id: Long? = null
)
