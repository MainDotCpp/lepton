package {{module.package}}.{{lowerCase biz}}.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import {{module.package}}.common.dal.annotations.QueryField
import {{module.package}}.common.dal.annotations.QueryMethod
import {{module.package}}.common.http.BaseQueryDTO
import {{module.package}}.{{lowerCase biz}}.enums. {{pascalCase biz}}TypeEnum
import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor

@NoArgsConstructor
@Schema(description = "租户查询参数")
class  {{pascalCase biz}}QueryDTO : BaseQueryDTO() {}