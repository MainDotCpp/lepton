package com.leuan.lepton.framework.config.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.config.dal.Config }
 */
@NoArgsConstructor
data class ConfigVO(
    var id: Long
) : Serializable