package com.leuan.lepton.role.service

import com.leuan.lepton.common.constants.BizErrEnum
import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.role.controller.dto.RoleQueryDTO
import com.leuan.lepton.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.role.controller.vo.RoleVO
import com.leuan.lepton.role.dal.QRole
import com.leuan.lepton.role.dal.RoleRepository
import com.leuan.lepton.role.mapping.RoleMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 系统角色服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[RoleService]
 */
@Service
class RoleService {

    @Resource
    private lateinit var roleMapper: RoleMapper

    @Resource
    private lateinit var roleRepository: RoleRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    /**
     * 通过id获取
     * @param [id] ID
     * @return [RoleVO]
     */
    fun getById(id: Long): RoleVO {
        val entity = roleRepository.findById(id).orElseThrow { BizErr(BizErrEnum.ROLE_NOT_FOUND) }
        return roleMapper.entityToVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: RoleQueryDTO) = arrayOf(
        queryDTO.id?.let { QRole.role.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<RoleVO>]
     */
    fun list(queryDTO: RoleQueryDTO): List<RoleVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(QRole.role)
            .where(*expressions)
            .fetch()
            .map(roleMapper::entityToVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<RoleVO>]
     */
    fun page(queryDTO: RoleQueryDTO): PageDTO<RoleVO> {
        val pageDTO = PageDTO<RoleVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(QRole.role)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(QRole.role.id.count()).from(QRole.role).where(*expressions).fetchOne() ?: 0
        pageDTO.records = query.fetch().map(roleMapper::entityToVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [roleSaveDTO] 系统角色保存传输层对象
     * @return [RoleVO]
     */
    fun save(roleSaveDTO: RoleSaveDTO): RoleVO {
        val entity = roleSaveDTO.id?.let {
            roleRepository.findById(it).orElseThrow { BizErr(BizErrEnum.ROLE_NOT_FOUND) }
        } ?: roleMapper.saveDtoToEntity(roleSaveDTO)
        roleRepository.save(entity)
        logInfo("保存系统角色成功|${entity.toJson()}")
        return roleMapper.entityToVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除系统角色|ID：$id")
        roleRepository.deleteById(id)
        return true
    }

}