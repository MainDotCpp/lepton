package {{module.package}}.{{lowerCase biz}}.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link {{module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}} }
 */
@NoArgsConstructor
data class {{pascalCase biz}}SaveDTO(
    val id: Long? = null
)
