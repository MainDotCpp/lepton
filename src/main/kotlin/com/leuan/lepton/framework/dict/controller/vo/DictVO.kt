package com.leuan.lepton.framework.dict.controller.vo

import com.leuan.lepton.framework.dict.dal.DictItem
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.dict.dal.Dict }
 */
@NoArgsConstructor
data class DictVO(
    var id: Long, var type: String = "", var name: String = "", var items: MutableSet<DictItem> = mutableSetOf()
) : Serializable