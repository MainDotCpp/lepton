package {{ module.package }}.{{ lowerCase biz }}.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import {{ module.package }}.{{ lowerCase biz}}.controller.dto.{{ pascalCase biz }}QueryDTO
import {{ module.package }}.{{ lowerCase biz}}.controller.dto.{{ pascalCase biz }}SaveDTO
import {{ module.package }}.{{ lowerCase biz}}.controller.vo.{{ pascalCase biz }}VO
import {{ module.package }}.{{ lowerCase biz}}.dal.Q{{ pascalCase biz }}
import {{ module.package }}.{{ lowerCase biz}}.dal.{{ pascalCase biz }}
import {{ module.package }}.{{ lowerCase biz}}.dal.{{ pascalCase biz }}Repository
import {{ module.package }}.{{ lowerCase biz}}.mapping.{{ pascalCase biz }}Mapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * {{ comment }}服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[{{ pascalCase biz }}Service]
 */
@Service
class {{ pascalCase biz }}Service {

    @Resource
    private lateinit var {{ camelCase biz }}Mapper: {{ pascalCase biz }}Mapper

    @Resource
    private lateinit var {{ camelCase biz }}Repository: {{ pascalCase biz }}Repository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val q{{ pascalCase biz }} = Q{{ pascalCase biz }}.{{ camelCase biz }}

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [{{ pascalCase biz }}VO]
     */
    fun getById(id: Long): {{ pascalCase biz }}VO {
        val entity = {{ camelCase biz }}Repository
            .findOne(q{{ pascalCase biz }}.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.{{ constantCase biz }}_NOT_FOUND) }
        return {{ camelCase biz }}Mapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<{{ pascalCase biz }}VO>]
     */
    fun list(queryDTO: {{ pascalCase biz }}QueryDTO): List<{{ pascalCase biz }}VO> {
        return jpaQueryFactory
            .selectFrom(q{{ pascalCase biz }})
            .buildExpressions(queryDTO)
            .fetch()
            .map({{ camelCase biz }}Mapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<{{ pascalCase biz }}VO>]
     */
    fun page(queryDTO: {{ pascalCase biz }}QueryDTO): PageDTO<{{ pascalCase biz }}VO> {
        val pageDTO = PageDTO<{{ pascalCase biz }}VO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(q{{ pascalCase biz }})
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(q{{ pascalCase biz }}.id.count()).from(q{{ pascalCase biz }}).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map({{ camelCase biz }}Mapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [{{ camelCase biz }}SaveDTO] {{ comment }}保存传输层对象
     * @return [{{ pascalCase biz }}VO]
     */
    fun save({{ camelCase biz }}SaveDTO: {{ pascalCase biz }}SaveDTO): {{ pascalCase biz }}VO {
        val entity = {{ camelCase biz }}SaveDTO.id?.let {
            {{ camelCase biz }}Repository.findById(it).orElseThrow { BizErr(BizErrEnum.{{ constantCase biz }}_NOT_FOUND) }
        } ?: {{ pascalCase biz }}()

        {{ camelCase biz }}Mapper.partialUpdate({{ camelCase biz }}SaveDTO, entity)
        {{ camelCase biz }}Repository.save(entity)
        return {{ camelCase biz }}Mapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        {{ camelCase biz }}Repository.deleteById(id)
        return true
    }

}