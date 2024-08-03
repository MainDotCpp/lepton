package com.leuan.lepton.tenant.service

import com.leuan.lepton.common.constants.BizErrEnum
import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.thread.getThreadContext
import com.leuan.lepton.common.utils.buildExpressions
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.role.dal.QRole
import com.leuan.lepton.role.dal.Role
import com.leuan.lepton.role.service.RoleService
import com.leuan.lepton.syspackage.dal.QSysPackage
import com.leuan.lepton.tenant.controller.dto.TenantQueryDTO
import com.leuan.lepton.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.tenant.controller.vo.TenantVO
import com.leuan.lepton.tenant.dal.QTenant
import com.leuan.lepton.tenant.dal.Tenant
import com.leuan.lepton.tenant.dal.TenantRepository
import com.leuan.lepton.tenant.mapping.TenantMapper
import com.leuan.lepton.user.dal.QUser
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
class TenantService {

    @Resource
    private lateinit var tenantMapper: TenantMapper

    @Resource
    private lateinit var tenantRepository: TenantRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var roleService: RoleService

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
    fun save(tenantSaveDTO: TenantSaveDTO): TenantVO {
        val entity = tenantSaveDTO.id?.let {
            tenantRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: Tenant()

        tenantMapper.partialUpdate(tenantSaveDTO, entity)
        tenantRepository.save(entity)
        logInfo("保存租户 ${entity.toJson()}")

        tenantSaveDTO.id ?: run {
            logInfo("首次创建, 添加管理员角色和超级账号到租户 ${entity.toJson()}")
            val qRole = QRole.role
            val menus = jpaQueryFactory.select(QSysPackage.sysPackage.menus).from(QSysPackage.sysPackage)
                .where(QSysPackage.sysPackage.id.eq(tenantSaveDTO.sysPackage.id)).fetchOne()
            val roleId = jpaQueryFactory
                .insert(qRole)
                .set(qRole.name, "管理员")
                .set(qRole.code, "admin")
                .set(qRole.tenantId, entity.id)
                .set(qRole.builtin, true)
                .set(qRole.menus, menus)
                .execute()

            // 添加超级账号到管理员角色
            val createdUser = jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.id.eq(getThreadContext().userId))
                .fetchOne()!!

            createdUser.roles.add(Role().apply { id = roleId })
            createdUser

        }


        return tenantMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        tenantRepository.deleteById(id)
        return true
    }

}