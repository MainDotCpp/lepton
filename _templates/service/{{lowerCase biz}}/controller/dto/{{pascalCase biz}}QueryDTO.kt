package {{package}}.{{camelCase biz}}.controller.dto

import {{package}}.common.http.PageParams
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "{{comment}}查询参数")
data class {{pascalCase biz}}QueryDTO(
    @Schema(description = "{{comment}}ID")
    val id: Long? = null,
    @Schema(description = "当前页")
    override var current: Long = 1,
    @Schema(description = "每页大小")
    override var pageSize: Long = 10
) : PageParams