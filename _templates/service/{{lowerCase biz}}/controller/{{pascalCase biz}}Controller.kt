package {{module.package}}.{{lowerCase biz}}.controller

import {{module.package}}.{{lowerCase biz}}.controller.dto.{{pascalCase biz}}QueryDTO
import {{module.package}}.{{lowerCase biz}}.controller.dto.{{pascalCase biz}}SaveDTO
import {{module.package}}.{{lowerCase biz}}.service.{{pascalCase biz}}Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@Tag(name = "{{pascalCase biz}}")
@RestController
@RequestMapping("{{camelCase biz}}")
class {{pascalCase biz}}Controller {

    @Resource
    private lateinit var {{camelCase biz}}Service: {{pascalCase biz}}Service

    @GetMapping("getById")
    @Operation(summary = "根据ID获取{{comment}}")
    @PreAuthorize("hasAnyAuthority('{{module.name}}:{{lowerCase biz}}:menu')")
    fun getById(id: Long) = {{camelCase biz}}Service.getById(id)

    @GetMapping("list")
    @Operation(summary = "查询{{comment}}列表")
    @PreAuthorize("hasAnyAuthority('{{module.name}}:{{lowerCase biz}}:menu')")
    fun list(queryDTO: {{pascalCase biz}}QueryDTO) = {{camelCase biz}}Service.list(queryDTO)

    @GetMapping("page")
    @Operation(summary = "分页查询{{comment}}列表")
    @PreAuthorize("hasAnyAuthority('{{module.name}}:{{lowerCase biz}}:menu')")
    fun page(queryDTO: {{pascalCase biz}}QueryDTO) = {{camelCase biz}}Service.page(queryDTO)

    @PostMapping("save")
    @Operation(summary = "保存{{comment}}")
    @PreAuthorize("hasAnyAuthority('{{module.name}}:{{lowerCase biz}}:update')")
    fun save(@RequestBody saveDTO: {{pascalCase biz}}SaveDTO) = {{camelCase biz}}Service.save(saveDTO)

    @GetMapping("deleteById")
    @Operation(summary = "根据ID删除{{comment}}")
    @PreAuthorize("hasAnyAuthority('{{module.name}}:{{lowerCase biz}}:delete')")
    fun deleteById(id: Long) = {{camelCase biz}}Service.deleteById(id)

    @GetMapping("export")
    @Operation(summary = "导出{{comment}}")
    @PreAuthorize("hasAnyAuthority('{{module.name}}:{{lowerCase biz}}:export')")
    fun export(queryDTO: {{pascalCase biz}}QueryDTO) = ""
}