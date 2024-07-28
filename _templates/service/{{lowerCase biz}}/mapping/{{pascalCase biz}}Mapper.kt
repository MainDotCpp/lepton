package {{module.package}}.{{camelCase biz}}.mapping

import {{module.package}}.{{camelCase biz}}.controller.dto.{{pascalCase biz}}SaveDTO
import {{module.package}}.{{camelCase biz}}.controller.vo.{{pascalCase biz}}VO
import {{module.package}}.{{camelCase biz}}.dal.{{pascalCase biz}}
import org.mapstruct.*

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
abstract class {{pascalCase biz}}Mapper {
    abstract fun toEntity({{camelCase biz}}VO: {{pascalCase biz}}VO): {{pascalCase biz}}
    abstract fun entityToVO({{camelCase biz}}: {{pascalCase biz}}): {{pascalCase biz}}VO

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate({{camelCase biz}}VO: {{pascalCase biz}}VO, @MappingTarget {{camelCase biz}}: {{pascalCase biz}}): {{pascalCase biz}}

    abstract fun saveDtoToEntity({{camelCase biz}}SaveDTO: {{pascalCase biz}}SaveDTO): {{pascalCase biz}}
}