package {{package}}.{{camelCase biz}}.controller

import {{package}}.{{camelCase biz}}.controller.dto.{{pascalCase biz}}QueryDTO
import {{package}}.{{camelCase biz}}.controller.dto.{{pascalCase biz}}SaveDTO
import {{package}}.{{camelCase biz}}.service.{{pascalCase biz}}Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*

@Tag(name = "{{pascalCase biz}}")
@RestController
@RequestMapping("{{camelCase biz}}")
class {{pascalCase biz}}Controller {

    @Resource
    private lateinit var {{camelCase biz}}Service: {{pascalCase biz}}Service

    @Parameter(name = "id", description = "{{comment}}ID", required = true)
    @Operation(summary = "根据ID获取{{comment}}")
    @GetMapping("getById")
    fun getById(id: Long) = {{camelCase biz}}Service.getById(id)

    @Operation(summary = "查询{{comment}}列表")
    @GetMapping("list")
    fun list(queryDTO: {{pascalCase biz}}QueryDTO = {{pascalCase biz}}QueryDTO()) = {{camelCase biz}}Service.list(queryDTO)

    @Operation(summary = "分页查询{{comment}}列表")
    @GetMapping("page")
    fun page(queryDTO: {{pascalCase biz}}QueryDTO = {{pascalCase biz}}QueryDTO()) = {{camelCase biz}}Service.page(queryDTO)

    @Operation(summary = "保存{{comment}}")
    @PostMapping("save")
    fun save(@RequestBody saveDTO: {{pascalCase biz}}SaveDTO) = {{camelCase biz}}Service.save(saveDTO)

    @Operation(summary = "根据ID删除{{comment}}")
    @GetMapping("deleteById")
    fun deleteById(id: Long) = {{camelCase biz}}Service.deleteById(id)
}