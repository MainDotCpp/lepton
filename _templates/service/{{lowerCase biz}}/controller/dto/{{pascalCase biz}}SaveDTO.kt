package {{lowerCase module.package}}.{{lowerCase biz}}.controller.dto

import com.leuan.lepton.common.annotations.NoArgs
/**
 * DTO for {@link {{lowerCase module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}} }
 */
@NoArgs
data class {{pascalCase biz}}SaveDTO(
    val id: Long? = null,
)
