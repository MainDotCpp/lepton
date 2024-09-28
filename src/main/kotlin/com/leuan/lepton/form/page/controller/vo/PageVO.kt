package com.leuan.lepton.form.page.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.from.page.dal.Page }
 */
@NoArgsConstructor
data class PageVO(
    var id: Long
) : Serializable