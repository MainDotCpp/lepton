package com.leuan.lepton.role.service

import com.leuan.lepton.common.constants.BizErrEnum
import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.role.controller.dto.RoleQueryDTO
import com.leuan.lepton.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.role.controller.vo.RoleVO
import com.leuan.lepton.role.dal.QRole
import com.leuan.lepton.role.dal.Role
import com.leuan.lepton.role.dal.RoleRepository
import com.leuan.lepton.role.mapping.RoleMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 角色服务
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

    private val qRole = QRole.role

    /**
     * 通过id获取角色
     * @param [id] ID
     * @return [RoleVO]
     */
    fun getById(id: Long): RoleVO {
        val entity = roleRepository
            .findOne(qRole.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        return roleMapper.toVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 查询传输层对象
     */
    private fun buildExpressions(queryDTO: RoleQueryDTO) = arrayOf(
        queryDTO.id?.let { qRole.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<RoleVO>]
     */
    fun list(queryDTO: RoleQueryDTO): List<RoleVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(qRole)
            .where(*expressions)
            .fetch()
            .map(roleMapper::toVO)
    }

    /**
     * 分页查询角色
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<RoleVO>]
     */
    fun page(queryDTO: RoleQueryDTO): PageDTO<RoleVO> {
        val pageDTO = PageDTO<RoleVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qRole)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qRole.id.count()).from(qRole).where(*expressions)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(roleMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [roleSaveDTO] 角色保存传输层对象
     * @return [RoleVO]
     */
    fun save(roleSaveDTO: RoleSaveDTO): RoleVO {
        val entity = roleSaveDTO.id?.let {
            roleRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: Role()

        roleMapper.partialUpdate(roleSaveDTO, entity)
        roleRepository.save(entity)
        return roleMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        roleRepository.deleteById(id)
        return true
    }

}