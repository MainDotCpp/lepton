package {{package}}.{{camelCase biz}}.controller.vo

import java.io.Serializable

/**
 * DTO for {@link {{package}}.{{camelCase biz}}.dal.{{pascalCase biz}} }
 */
data class `{{pascalCase biz}}VO`(
    var id: Long? = null,
    var name: String = ""
) : Serializable