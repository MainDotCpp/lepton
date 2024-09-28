package com.leuan.lepton.form.component.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.from.component.dal.Component }
 */
@NoArgsConstructor
data class ComponentVO(
    var id: Long
) : Serializable