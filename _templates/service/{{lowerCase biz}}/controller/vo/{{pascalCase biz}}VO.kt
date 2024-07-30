package

{ { lowerCase module .package } }.{ { lowerCase biz } }.controller.vo

import java.io.Serializable
import com.leuan.lepton.common.annotations.NoArgs

/**
 * DTO for {@link {{lowerCase module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}} }
 */
@NoArgs
data class {
    { pascalCase biz }
}VO(
var id: Long
) : Serializable