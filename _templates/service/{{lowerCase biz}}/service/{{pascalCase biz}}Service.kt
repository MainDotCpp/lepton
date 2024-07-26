package {{package}}.{{camelCase biz}}.service

import {{package}}.common.exception.BizErr
import {{package}}.common.http.PageDTO
import {{package}}.common.log.logInfo
import {{package}}.common.utils.toJson
import {{package}}.constants.BizErrEnum
import {{package}}.{{camelCase biz}}.controller.dto.{{pascalCase biz}}QueryDTO
import {{package}}.{{camelCase biz}}.controller.dto.{{pascalCase biz}}SaveDTO
import {{package}}.{{camelCase biz}}.controller.vo.{{pascalCase biz}}VO
import {{package}}.{{camelCase biz}}.dal.Q{{pascalCase biz}}
import {{package}}.{{camelCase biz}}.dal.{{pascalCase biz}}Repository
import {{package}}.{{camelCase biz}}.mapping.{{pascalCase biz}}Mapper
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

    /**
     * 通过id获取
     * @param [id] ID
     * @return [{{pascalCase biz}}VO]
     */
    fun getById(id: Long): {{pascalCase biz}}VO {
        val entity = {{camelCase biz}}Repository.findById(id).orElseThrow { BizErr(BizErrEnum.{{constantCase biz}}_NOT_FOUND) }
        return {{camelCase biz}}Mapper.entityToVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: {{pascalCase biz}}QueryDTO) = arrayOf(
        queryDTO.id?.let { Q{{pascalCase biz}}.{{camelCase biz}}.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<{{pascalCase biz}}VO>]
     */
    fun list(queryDTO: {{pascalCase biz}}QueryDTO): List<{{pascalCase biz}}VO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(Q{{pascalCase biz}}.{{camelCase biz}})
            .where(*expressions)
            .fetch()
            .map({{camelCase biz}}Mapper::entityToVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<{{pascalCase biz}}VO>]
     */
    fun page(queryDTO: {{pascalCase biz}}QueryDTO): PageDTO<{{pascalCase biz}}VO> {
        val pageDTO = PageDTO<{{pascalCase biz}}VO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(Q{{pascalCase biz}}.{{camelCase biz}})
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(Q{{pascalCase biz}}.{{camelCase biz}}.id.count()).from(Q{{pascalCase biz}}.{{camelCase biz}}).where(*expressions).fetchOne() ?: 0
        pageDTO.records = query.fetch().map({{camelCase biz}}Mapper::entityToVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [{{camelCase biz}}SaveDTO] {{comment}}保存传输层对象
     * @return [{{pascalCase biz}}VO]
     */
    fun save({{camelCase biz}}SaveDTO: {{pascalCase biz}}SaveDTO): {{pascalCase biz}}VO {
        val entity = {{camelCase biz}}SaveDTO.id?.let {
            {{camelCase biz}}Repository.findById(it).orElseThrow { BizErr(BizErrEnum.{{constantCase biz}}_NOT_FOUND) }
        } ?: {{camelCase biz}}Mapper.saveDtoToEntity({{camelCase biz}}SaveDTO)
        {{camelCase biz}}Repository.save(entity)
        logInfo("保存{{comment}}成功|${entity.toJson()}")
        return {{camelCase biz}}Mapper.entityToVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除{{comment}}|ID：$id")
        {{camelCase biz}}Repository.deleteById(id)
        return true
    }

}