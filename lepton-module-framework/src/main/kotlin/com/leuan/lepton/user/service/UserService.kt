package com.leuan.lepton.user.service

import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.thread.getThreadContext
import com.leuan.lepton.common.utils.cache
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.common.constants.BizErrEnum
import com.leuan.lepton.common.constants.SESSION_CACHE_PREFIX
import com.leuan.lepton.user.controller.dto.UserQueryDTO
import com.leuan.lepton.user.controller.dto.UserSaveDTO
import com.leuan.lepton.user.controller.vo.UserInfoVO
import com.leuan.lepton.user.controller.vo.UserVO
import com.leuan.lepton.user.dal.QUser
import com.leuan.lepton.user.dal.User
import com.leuan.lepton.user.dal.UserRepository
import com.leuan.lepton.user.mapping.UserMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service

/**
 * 系统用户服务
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
    private lateinit var redissonClient: RedissonClient

    /**
     * 通过id获取
     * @param [id] ID
     * @return [UserVO]
     */
    fun getById(id: Long): UserVO {
        val entity = userRepository.findById(id).orElseThrow { BizErr(BizErrEnum.USER_NOT_FOUND) }
        return userMapper.entityToVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: UserQueryDTO) = arrayOf(
        queryDTO.id?.let { QUser.user.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<UserVO>]
     */
    fun list(queryDTO: UserQueryDTO): List<UserVO> {
        logInfo("查询系统用户列表|${queryDTO.toJson()}")
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(QUser.user)
            .where(*expressions)
            .fetch()
            .map(userMapper::entityToVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<UserVO>]
     */
    fun page(queryDTO: UserQueryDTO): PageDTO<UserVO> {
        val pageDTO = PageDTO<UserVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(QUser.user)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(QUser.user.id.count()).from(QUser.user).where(*expressions).fetchOne() ?: 0
        pageDTO.records = query.fetch().map(userMapper::entityToVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [userSaveDTO] 系统用户保存传输层对象
     * @return [UserVO]
     */
    fun save(userSaveDTO: UserSaveDTO): UserVO {
        val entity = userSaveDTO.id?.let {
            userRepository.findById(it).orElseThrow { BizErr(BizErrEnum.USER_NOT_FOUND) }
        } ?: userMapper.saveDtoToEntity(userSaveDTO)
        userRepository.save(entity)
        logInfo("保存系统用户成功|${entity.toJson()}")
        return userMapper.entityToVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除系统用户|ID：$id")
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

    fun getUserInfo(id: Long? = getThreadContext().userId, freshCache: Boolean = false): UserInfoVO =
        cache("session:${id}", fresh = freshCache) {
            if (id == null) throw BizErr(BizErrEnum.NOT_LOGIN)
            val user = userRepository.findById(id).orElseThrow { BizErr(BizErrEnum.USER_NOT_FOUND) }
            val userInfo = userMapper.toDto(user)
            val context = getThreadContext()
            redissonClient
                .getBucket<UserInfoVO>("$SESSION_CACHE_PREFIX:${context.userId}")
                .set(userInfo)
            userInfo
        }

}