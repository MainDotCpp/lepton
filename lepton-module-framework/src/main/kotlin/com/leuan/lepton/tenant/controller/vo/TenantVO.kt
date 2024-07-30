package com.leuan.lepton.tenant.controller.vo

import com.leuan.lepton.tenant.enums.TenantTypeEnum
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.tenant.dal.Tenant}
 */
@NoArgsConstructor
data class TenantVO(
    var id: Long? = null,
    var name: String = "",
    var type: TenantTypeEnum = TenantTypeEnum.ACTIVE,
    var sysPackageId: Long? = null,
    var sysPackageName: String? = ""
) : Serializable