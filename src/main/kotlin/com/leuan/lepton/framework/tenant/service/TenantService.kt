package com.leuan.lepton.framework.tenant.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.log.logInfo
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.common.thread.ignoreTenantId
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.common.utils.toJson
import com.leuan.lepton.framework.role.dal.QRole
import com.leuan.lepton.framework.role.dal.Role
import com.leuan.lepton.framework.role.service.RoleService
import com.leuan.lepton.framework.syspackage.dal.QSysPackage
import com.leuan.lepton.framework.syspackage.dal.SysPackageRepository
import com.leuan.lepton.framework.tenant.controller.dto.TenantQueryDTO
import com.leuan.lepton.framework.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.framework.tenant.controller.vo.TenantVO
import com.leuan.lepton.framework.tenant.dal.QTenant
import com.leuan.lepton.framework.tenant.dal.Tenant
import com.leuan.lepton.framework.tenant.dal.TenantRepository
import com.leuan.lepton.framework.tenant.mapping.TenantMapper
import com.leuan.lepton.framework.user.dal.QUser
import com.leuan.lepton.framework.user.service.UserService
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 租户服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[TenantService]
 */
@Service
class TenantService(private val sysPackageRepository: SysPackageRepository) {

    @Resource
    private lateinit var tenantMapper: TenantMapper

    @Resource
    private lateinit var tenantRepository: TenantRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var roleService: RoleService

    @Resource
    private lateinit var userService: UserService

    private val qTenant = QTenant.tenant

    /**
     * 通过id获取租户
     * @param [id] ID
     * @return [TenantVO]
     */
    fun getById(id: Long): TenantVO {
        val entity = tenantRepository
            .findOne(qTenant.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        return tenantMapper.toVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 查询传输层对象
     */
    private fun buildWhere(queryDTO: TenantQueryDTO) = arrayOf(
        qTenant.id.`in`(queryDTO.id),
    )


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<TenantVO>]
     */
    fun list(queryDTO: TenantQueryDTO): List<TenantVO> {
        return jpaQueryFactory
            .selectFrom(qTenant)
            .buildExpressions(queryDTO)
            .fetch()
            .map(tenantMapper::toVO)
    }

    /**
     * 分页查询租户
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<TenantVO>]
     */
    fun page(queryDTO: TenantQueryDTO): PageDTO<TenantVO> {
        logInfo("分页查询租户 ${queryDTO.toJson()}")
        val pageDTO = PageDTO<TenantVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qTenant)
            .buildExpressions(queryDTO)
        pageDTO.total =
            jpaQueryFactory.select(qTenant.id.count()).from(qTenant).buildExpressions(queryDTO, false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(tenantMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [tenantSaveDTO] 租户保存传输层对象
     * @return [TenantVO]
     */
    @Transactional(rollbackFor = [Exception::class])
    fun save(tenantSaveDTO: TenantSaveDTO): TenantVO = ignoreTenantId {
        val entity = tenantSaveDTO.id?.let {
            tenantRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: Tenant()

        tenantMapper.partialUpdate(tenantSaveDTO, entity)
        tenantRepository.save(entity)
        logInfo("保存租户 ${entity.toJson()}")

        tenantSaveDTO.id?.let {
            val sysPackage = jpaQueryFactory.selectFrom(QSysPackage.sysPackage)
                .where(QSysPackage.sysPackage.id.eq(entity.sysPackage!!.id))
                .fetchOne() ?: throw BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND)
            val menus = sysPackage!!.menus.map { it }.toMutableSet()
            logInfo("更新租户下所有用户的菜单权限:${menus.map { it.permission }}")

            // 更新租户下所有用户的菜单权限
            val roles = jpaQueryFactory.selectFrom(QRole.role).where(QRole.role.tenantId.eq(entity.id)).fetch()

            // 在所有角色的菜单中删除超出套餐的菜单
            roles.forEach { role ->
                role.menus = role.menus.filter { menu -> menus.any { it.id == menu.id } }.toMutableSet()
            }

            val adminRole = roles.find { it.code == "admin" }!!
            adminRole.menus = menus
            roleService.save(adminRole)

        } ?: run {
            logInfo("首次创建, 添加管理员角色和超级账号到租户 ${entity.toJson()}")
            val sysPackage = jpaQueryFactory.select(QSysPackage.sysPackage).from(QSysPackage.sysPackage)
                .where(QSysPackage.sysPackage.id.eq(entity.sysPackage!!.id)).fetchOne()
                ?: throw BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND)

            // 添加超级账号到管理员角色
            val createdUser = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.id.eq(getThreadContext().userId))
                .fetchOne()!!

            val role = roleService.save(Role().apply {
                this.name = "管理员"
                this.code = "admin"
                this.menus = sysPackage.menus.map { it }.toMutableSet()
                this.builtin = true
                this.tenantId = entity.id!!
                this.users = mutableSetOf(createdUser)
            })
            roleService.save(role)

            createdUser.roles.add(Role().apply { id = role.id })

            logInfo("添加新租户到管理员角色")
            createdUser.tenants.add(entity)
            userService.save(createdUser)
            logInfo("刷新用户缓存")
            userService.getUserInfo(freshCache = true)
            createdUser
        }

        return@ignoreTenantId tenantMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    @Transactional(rollbackFor = [Exception::class])
    fun deleteById(id: Long): Boolean = ignoreTenantId {
        val tenant =
            tenantRepository.findOne(qTenant.id.eq(id)).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        // 取消租户下所有用户的角色
        tenant.users.forEach {
            it.roles = it.roles.filter { role -> role.tenantId != id }.toMutableSet()
            it.tenants = it.tenants.filter { tenant -> tenant.id != id }.toMutableSet()
        }

        tenant.sysPackage = null

        // 删除角色
        jpaQueryFactory.delete(QRole.role).where(QRole.role.tenantId.eq(id)).execute()
        tenantRepository.save(tenant)
        tenantRepository.deleteById(id)
        return@ignoreTenantId true
    }

}