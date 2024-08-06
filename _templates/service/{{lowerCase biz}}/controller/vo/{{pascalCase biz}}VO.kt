package {{module.package}}.{{lowerCase biz}}.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link {{module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}} }
 */
@NoArgsConstructor
data class {{pascalCase biz}}VO(
    var id: Long
) : Serializable