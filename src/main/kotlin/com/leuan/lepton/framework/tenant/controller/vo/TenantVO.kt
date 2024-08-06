package com.leuan.lepton.framework.tenant.controller.vo

import com.leuan.lepton.framework.tenant.enums.TenantTypeEnum
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.framework.tenant.dal.Tenant }
 */
@NoArgsConstructor
data class TenantVO(
    var id: Long,
    var logo: String = "",
    var name: String = "",
    var type: TenantTypeEnum = TenantTypeEnum.ACTIVE,
    var code: String = "",
    var packageId: Long? = null,
    var packageName: String? = ""
) : Serializable