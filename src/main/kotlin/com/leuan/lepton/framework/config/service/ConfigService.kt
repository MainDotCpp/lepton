package com.leuan.lepton.framework.config.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.config.controller.dto.ConfigQueryDTO
import com.leuan.lepton.framework.config.controller.dto.ConfigSaveDTO
import com.leuan.lepton.framework.config.controller.vo.ConfigVO
import com.leuan.lepton.framework.config.dal.Config
import com.leuan.lepton.framework.config.dal.ConfigRepository
import com.leuan.lepton.framework.config.dal.QConfig
import com.leuan.lepton.framework.config.dal.TenantConfig
import com.leuan.lepton.framework.config.enums.ConfigType
import com.leuan.lepton.framework.config.mapping.ConfigMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 系统设置服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[ConfigService]
 */
@Service
class ConfigService {

    @Resource
    private lateinit var configMapper: ConfigMapper

    @Resource
    private lateinit var configRepository: ConfigRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qConfig = QConfig.config

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [ConfigVO]
     */
    fun getById(id: Long): ConfigVO {
        val entity = configRepository
            .findOne(qConfig.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.CONFIG_NOT_FOUND) }
        return configMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<ConfigVO>]
     */
    fun list(queryDTO: ConfigQueryDTO): List<ConfigVO> {
        return jpaQueryFactory
            .selectFrom(qConfig)
            .buildExpressions(queryDTO)
            .fetch()
            .map(configMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<ConfigVO>]
     */
    fun page(queryDTO: ConfigQueryDTO): PageDTO<ConfigVO> {
        val pageDTO = PageDTO<ConfigVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qConfig)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qConfig.id.count()).from(qConfig).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(configMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [configSaveDTO] 系统设置保存传输层对象
     * @return [ConfigVO]
     */
    fun save(configSaveDTO: ConfigSaveDTO): ConfigVO {
        val entity = configSaveDTO.id?.let {
            configRepository.findById(it).orElseThrow { BizErr(BizErrEnum.CONFIG_NOT_FOUND) }
        } ?: Config()

        configMapper.partialUpdate(configSaveDTO, entity)
        configRepository.save(entity)
        return configMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        configRepository.deleteById(id)
        return true
    }

    fun saveTenantConfig(tenantConfig: TenantConfig): Any {
        val context = getThreadContext()
        val old = jpaQueryFactory.selectFrom(qConfig)
            .where(qConfig.tenantId.eq(context.tenantId))
            .fetchOne()
            ?: Config().apply {
                this.type = ConfigType.TENANT
                this.tenantId = context.tenantId
                this.tenantConfig = tenantConfig
            }.let {
                configRepository.save(it)
            }

        configMapper.partialUpdate(tenantConfig, old.tenantConfig!!)
        configRepository.save(old)
        return true
    }

    fun getConfig(): Config {
        val config = Config()
        config.globalConfig = jpaQueryFactory.selectFrom(qConfig)
            .where(qConfig.type.eq(ConfigType.GLOBAL))
            .fetchOne()!!.globalConfig
        config.tenantConfig = jpaQueryFactory.selectFrom(qConfig)
            .where(qConfig.type.eq(ConfigType.TENANT))
            .fetchOne()?.tenantConfig ?: TenantConfig()
        return config
    }

}