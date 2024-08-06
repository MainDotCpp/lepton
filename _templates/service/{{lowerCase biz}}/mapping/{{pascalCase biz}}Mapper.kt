package {{module.package}}.{{lowerCase biz}}.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import {{module.package}}.{{lowerCase biz}}.controller.dto.{{pascalCase biz}}SaveDTO
import {{module.package}}.{{lowerCase biz}}.controller.dto.{{pascalCase biz}}QueryDTO
import {{module.package}}.{{lowerCase biz}}.controller.vo.{{pascalCase biz}}VO
import {{module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}}
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class {{pascalCase biz}}Mapper {
    abstract fun toEntity({{camelCase biz}}VO: {{pascalCase biz}}VO): {{pascalCase biz}}
    abstract fun toEntity({{camelCase biz}}QueryDTO: {{pascalCase biz}}QueryDTO): {{pascalCase biz}}
    abstract fun toVO({{camelCase biz}}: {{pascalCase biz}}): {{pascalCase biz}}VO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: {{pascalCase biz}}SaveDTO, @MappingTarget {{camelCase biz}}: {{pascalCase biz}}): {{pascalCase biz}}

    fun idToEntity(id: Long?): {{pascalCase biz}}? {
        return id?.let { {{pascalCase biz}}().apply { this.id = it } }
    }

    fun entityToId({{camelCase biz}}: {{pascalCase biz}}?): Long? {
        return {{camelCase biz}}?.id
    }

}