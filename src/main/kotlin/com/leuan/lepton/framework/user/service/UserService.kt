package com.leuan.lepton.framework.user.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.constants.SESSION_CACHE_PREFIX
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.log.logDebug
import com.leuan.lepton.framework.common.log.logInfo
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.common.utils.cache
import com.leuan.lepton.framework.dept.dal.QDept
import com.leuan.lepton.framework.tenant.dal.Tenant
import com.leuan.lepton.framework.user.controller.dto.UserQueryDTO
import com.leuan.lepton.framework.user.controller.dto.UserSaveDTO
import com.leuan.lepton.framework.user.controller.vo.UserInfoVO
import com.leuan.lepton.framework.user.controller.vo.UserOptionsVO
import com.leuan.lepton.framework.user.controller.vo.UserVO
import com.leuan.lepton.framework.user.dal.QSysUserTenant
import com.leuan.lepton.framework.user.dal.QUser
import com.leuan.lepton.framework.user.dal.User
import com.leuan.lepton.framework.user.dal.UserRepository
import com.leuan.lepton.framework.user.enums.DataPermissionType
import com.leuan.lepton.framework.user.mapping.UserMapper
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 用户服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[UserService]
 */
@Service
class UserService {

    @Resource
    private lateinit var userMapper: UserMapper

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Resource
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    private val qUser = QUser.user

    /**
     * 通过id获取用户
     * @param [id] ID
     * @return [UserVO]
     */
    fun getById(id: Long): UserVO {
        val entity = userRepository
            .findOne(qUser.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        val userVO = userMapper.toVO(entity)
        userVO.dataPermission = jpaQueryFactory.select(QSysUserTenant.sysUserTenant.dataPermission)
            .from(QSysUserTenant.sysUserTenant)
            .where(QSysUserTenant.sysUserTenant.user.id.eq(id))
            .where(QSysUserTenant.sysUserTenant.tenant.id.eq(getThreadContext().tenantId))
            .fetchOne()
        return userVO
    }

    /**
     * 构建表达式
     * @param [queryDTO] 查询传输层对象
     */
    private fun buildExpressions(queryDTO: UserQueryDTO) = arrayOf(
        queryDTO.id?.let { qUser.id.eq(it) },
        qUser.tenants.any().id.eq(getThreadContext().tenantId),
    )

    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<UserVO>]
     */
    fun list(queryDTO: UserQueryDTO): List<UserVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(qUser)
            .where(*expressions)
            .fetch()
            .map(userMapper::toVO)
    }

    /**
     * 分页查询用户
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<UserVO>]
     */
    fun page(queryDTO: UserQueryDTO): PageDTO<UserVO> {
        val pageDTO = PageDTO<UserVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qUser)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qUser.id.count()).from(qUser).where(*expressions)
                .fetchOne()!!

        pageDTO.data = query.fetch().map {
            UserVO(
                id = it.id,
                name = it.name,
                phone = it.phone,
                avatar = it.avatar,
                roleIds = it.roles
                    .filter { role -> role.tenantId == getThreadContext().tenantId }
                    .map { role -> role.id }.toMutableSet(),
                dataPermission = DataPermissionType.SELF
            )
        }

        val datePermissions = jpaQueryFactory.selectFrom(QSysUserTenant.sysUserTenant)
            .where(
                QSysUserTenant.sysUserTenant.user.id.`in`(pageDTO.data.map { it.id }),
                QSysUserTenant.sysUserTenant.tenant.id.eq(getThreadContext().tenantId)
            ).fetch()

        pageDTO.data.forEach { user ->
            datePermissions.find { it.id!!.userId == user.id }?.let {
                user.dataPermission = it.dataPermission
                user.deptId = it.deptId
            }
        }
        return pageDTO
    }

    /**
     * 保存
     * @param [userSaveDTO] 用户保存传输层对象
     * @return [UserVO]
     */
    @Transactional(rollbackFor = [Exception::class])
    fun save(userSaveDTO: UserSaveDTO): UserVO {
        val entity = userSaveDTO.id?.let {
            userRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: User()


        // 校验手机号是否重复
        if (entity.phone != userSaveDTO.phone && userRepository.exists(qUser.phone.eq(userSaveDTO.phone))) {
            throw BizErr(BizErrEnum.USER_PHONE_EXIST)
        }

        // 密码加密
        if (userSaveDTO.password != null) {
            userSaveDTO.password = passwordEncoder.encode(userSaveDTO.password)
        }


        userMapper.partialUpdate(userSaveDTO, entity)
        userRepository.save(entity)

        // 添加租户
        if (entity.tenants.none { it.id == getThreadContext().tenantId })
            entity.tenants.add(Tenant().apply { id = getThreadContext().tenantId })

        // 更新数据权限
        jpaQueryFactory.update(QSysUserTenant.sysUserTenant)
            .set(QSysUserTenant.sysUserTenant.dataPermission, userSaveDTO.dataPermission)
            .set(QSysUserTenant.sysUserTenant.deptId, userSaveDTO.deptId)
            .where(QSysUserTenant.sysUserTenant.user.id.eq(entity.id))
            .where(QSysUserTenant.sysUserTenant.tenant.id.eq(getThreadContext().tenantId))
            .execute()

        // 刷新缓存
        this.getUserInfo(entity.id, true, tenantId = getThreadContext().tenantId)

        // 更新角色
        return userMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        userRepository.deleteById(id)
        return true
    }

    /**
     * 通过手机号获取用户
     * @param [phone] 电话
     * @return [User?]
     */
    fun getUserByPhone(phone: String): User? {
        return jpaQueryFactory.selectFrom(QUser.user)
            .where(QUser.user.phone.eq(phone))
            .fetchOne()
    }

    /**
     * 获取用户信息
     * @param [id] ID
     * @param [freshCache] 新鲜缓存
     * @return [UserInfoVO]
     */
    fun getUserInfo(
        id: Long? = getThreadContext().userId,
        freshCache: Boolean = false,
        tenantId: Long = 0
    ): UserInfoVO =
        cache(
            "$SESSION_CACHE_PREFIX:$tenantId:${id}",
            fresh = freshCache,
            ttl = 3600L * 24 * 3,
            enable = tenantId != 0L
        ) {
            if (id == null) throw BizErr(BizErrEnum.NOT_LOGIN)
            val user = jpaQueryFactory
                .selectFrom(QUser.user)
                .leftJoin(QUser.user.roles).fetchJoin()
                .where(QUser.user.id.eq(id))
                .fetchOne() ?: throw BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND)
            val userInfo = userMapper.toDto(user)
            userInfo.roles = user.roles.filter { role -> role.tenantId == tenantId }.map { it.code }.toMutableSet()
            userInfo.permissions =
                user.roles.filter { role -> role.tenantId == tenantId }.flatMap { it.menus }.map { it.permission }
                    .toMutableSet()
            logDebug("用户角色: ${userInfo.roles}")
            logDebug("用户权限: ${userInfo.permissions}")

            // 获取数据权限
            val userTenant = jpaQueryFactory.select(QSysUserTenant.sysUserTenant)
                .from(QSysUserTenant.sysUserTenant)
                .where(QSysUserTenant.sysUserTenant.user.id.eq(id))
                .where(QSysUserTenant.sysUserTenant.tenant.id.eq(tenantId))
                .fetchOne()

            userInfo.dataPermission = userTenant?.dataPermission ?: DataPermissionType.SELF
            // 获取部门信息
            userInfo.deptCode = jpaQueryFactory.select(QDept.dept.code)
                .from(QDept.dept)
                .where(QDept.dept.id.eq(userTenant?.deptId ?: 0L))
                .fetchOne()
            userInfo
        }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun test(): Any {
        return jpaQueryFactory
            .from(qUser)
            .fetch()
    }

    fun getUserOptions(): MutableList<UserOptionsVO>? {
        return jpaQueryFactory
            .select(
                Projections.fields(
                    UserOptionsVO::class.java,
                    qUser.id,
                    qUser.name,
                    qUser.avatar
                )
            )
            .from(qUser)
            .where(qUser.tenants.any().id.eq(getThreadContext().tenantId))
            .fetch()
    }


}