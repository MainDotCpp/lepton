package com.leuan.lepton.framework.role.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.menu.controller.dto.MenuQueryDTO
import com.leuan.lepton.framework.menu.dal.Menu
import com.leuan.lepton.framework.menu.service.MenuService
import com.leuan.lepton.framework.role.controller.dto.RoleQueryDTO
import com.leuan.lepton.framework.role.controller.dto.RoleSaveDTO
import com.leuan.lepton.framework.role.controller.vo.RoleVO
import com.leuan.lepton.framework.role.dal.QRole
import com.leuan.lepton.framework.role.dal.Role
import com.leuan.lepton.framework.role.dal.RoleRepository
import com.leuan.lepton.framework.role.mapping.RoleMapper
import com.leuan.lepton.framework.user.service.UserService
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 角色服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[RoleService]
 */
@Service
class RoleService {

    @Autowired
    private lateinit var userService: UserService

    @Resource
    private lateinit var roleMapper: RoleMapper

    @Resource
    private lateinit var roleRepository: RoleRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var menuService: MenuService

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
        val roleVO = roleMapper.toVO(entity)

        // 超级管理员拥有所有菜单权限
        if (entity.code == "super_admin") {
            roleVO.menuIds = menuService.list(MenuQueryDTO()).map { it.id }.toMutableSet()
        }
        return roleVO
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<RoleVO>]
     */
    fun list(queryDTO: RoleQueryDTO): List<RoleVO> {
        return jpaQueryFactory
            .selectFrom(qRole)
            .buildExpressions(queryDTO)
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
        val query = jpaQueryFactory
            .selectFrom(qRole)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qRole.id.count()).from(qRole).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(roleMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [roleSaveDTO] 角色保存传输层对象
     * @return [RoleVO]
     */
    @Transactional(rollbackFor = [Exception::class])
    fun save(roleSaveDTO: RoleSaveDTO): RoleVO {
        if (roleSaveDTO.code == "super_admin") {
            throw BizErr(BizErrEnum.ILLEGAL_OPERATION)
        }
        val entity = roleSaveDTO.id?.let {
            roleRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: Role()

        roleSaveDTO.menuIds = menuService.findAncestorsByMenuIds(roleSaveDTO.menuIds ?: mutableSetOf())
        roleMapper.partialUpdate(roleSaveDTO, entity)
        roleRepository.save(entity)

        updateRoleMenus(entity.id, roleSaveDTO.menuIds ?: mutableSetOf())
        return roleMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    @Transactional(rollbackFor = [Exception::class])
    fun deleteById(id: Long): Boolean {
        val role =
            roleRepository.findOne(qRole.id.eq(id)).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        role.users.forEach { it.roles.remove(role) }
        roleRepository.save(role)
        roleRepository.deleteById(id)
        return true
    }

    fun save(role: Role): Role {
        return roleRepository.save(role)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun updateRoleMenus(roleId: Long, menuIds: Set<Long>) {
        val role = roleRepository.findOne(qRole.id.eq(roleId)).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        role.menus.clear()
        role.menus.addAll(menuIds.map { menuId -> Menu().apply { this.id = menuId } })
        roleRepository.save(role)
        flushSessionByRoleId(roleId)
    }

    fun flushSessionByRoleId(roleId: Long) {
        val role = roleRepository.findById(roleId).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        role.users.forEach { userService.getUserInfo(it.id) }
    }

}