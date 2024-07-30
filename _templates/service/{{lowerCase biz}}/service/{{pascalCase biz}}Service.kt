package {{module.package}}.{{lowerCase biz}}.service

import {{basePackage}}.common.constants.BizErrEnum
import {{basePackage}}.common.exception.BizErr
import {{basePackage}}.common.http.PageDTO
import {{module.package}}.{{lowerCase biz}}.controller.dto.{{pascalCase biz}}QueryDTO
import {{module.package}}.{{lowerCase biz}}.controller.dto.{{pascalCase biz}}SaveDTO
import {{module.package}}.{{lowerCase biz}}.controller.vo.{{pascalCase biz}}VO
import {{module.package}}.{{lowerCase biz}}.dal.Q{{pascalCase biz}}
import {{module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}}
import {{module.package}}.{{lowerCase biz}}.dal.{{pascalCase biz}}Repository
import {{module.package}}.{{lowerCase biz}}.mapping.{{pascalCase biz}}Mapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * {{comment}}服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[{{pascalCase biz}}Service]
 */
@Service
class {{pascalCase biz}}Service {

    @Resource
    private lateinit var {{camelCase biz}}Mapper: {{pascalCase biz}}Mapper

    @Resource
    private lateinit var {{camelCase biz}}Repository: {{pascalCase biz}}Repository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val q{{pascalCase biz}} = Q{{pascalCase biz}}.{{camelCase biz}}

    /**
     * 通过id获取{{comment}}
     * @param [id] ID
     * @return [{{pascalCase biz}}VO]
     */
    fun getById(id: Long): {{pascalCase biz}}VO {
        val entity = {{camelCase biz}}Repository
            .findOne(q{{pascalCase biz}}.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        return {{camelCase biz}}Mapper.toVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 查询传输层对象
     */
    private fun buildExpressions(queryDTO: {{pascalCase biz}}QueryDTO) = arrayOf(
        queryDTO.id?.let { q{{pascalCase biz}}.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<{{pascalCase biz}}VO>]
     */
    fun list(queryDTO: {{pascalCase biz}}QueryDTO): List<{{pascalCase biz}}VO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(q{{pascalCase biz}})
            .where(*expressions)
            .fetch()
            .map({{camelCase biz}}Mapper::toVO)
    }

    /**
     * 分页查询{{comment}}
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<{{pascalCase biz}}VO>]
     */
    fun page(queryDTO: {{pascalCase biz}}QueryDTO): PageDTO<{{pascalCase biz}}VO> {
        val pageDTO = PageDTO<{{pascalCase biz}}VO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(q{{pascalCase biz}})
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(q{{pascalCase biz}}.id.count()).from(q{{pascalCase biz}}).where(*expressions)
                .fetchOne()!!
        pageDTO.records = query.fetch().map({{camelCase biz}}Mapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [{{camelCase biz}}SaveDTO] {{comment}}保存传输层对象
     * @return [{{pascalCase biz}}VO]
     */
    fun save({{camelCase biz}}SaveDTO: {{pascalCase biz}}SaveDTO): {{pascalCase biz}}VO {
        val entity = {{camelCase biz}}SaveDTO.id?.let {
            {{camelCase biz}}Repository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: {{pascalCase biz}}()

        {{camelCase biz}}Mapper.partialUpdate({{camelCase biz}}SaveDTO, entity)
        {{camelCase biz}}Repository.save(entity)
        return {{camelCase biz}}Mapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        {{camelCase biz}}Repository.deleteById(id)
        return true
    }

}