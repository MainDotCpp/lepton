package com.leuan.lepton.tenant.service

import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.constants.BizErrEnum
import com.leuan.lepton.tenant.controller.dto.TenantQueryDTO
import com.leuan.lepton.tenant.controller.dto.TenantSaveDTO
import com.leuan.lepton.tenant.controller.vo.TenantVO
import com.leuan.lepton.tenant.dal.QTenant
import com.leuan.lepton.tenant.dal.TenantRepository
import com.leuan.lepton.tenant.mapping.TenantMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

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

    /**
     * 通过id获取
     * @param [id] ID
     * @return [TenantVO]
     */
    fun getById(id: Long): TenantVO {
        val entity = tenantRepository.findById(id).orElseThrow { BizErr(BizErrEnum.TENANT_NOT_FOUND) }
        return tenantMapper.entityToVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: TenantQueryDTO) = arrayOf(
        queryDTO.id?.let { QTenant.tenant.id.eq(it) },
        queryDTO.name?.let { QTenant.tenant.name.eq(it) }
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<TenantVO>]
     */
    fun list(queryDTO: TenantQueryDTO): List<TenantVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(QTenant.tenant)
            .where(*expressions)
            .fetch()
            .map(tenantMapper::entityToVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<TenantVO>]
     */
    fun page(queryDTO: TenantQueryDTO): PageDTO<TenantVO> {
        val pageDTO = PageDTO<TenantVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(QTenant.tenant)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(QTenant.tenant.id.count()).from(QTenant.tenant).where(*expressions).fetchOne() ?: 0
        pageDTO.records = query.fetch().map(tenantMapper::entityToVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [tenantSaveDTO] 租户保存传输层对象
     * @return [TenantVO]
     */
    fun save(tenantSaveDTO: TenantSaveDTO): TenantVO {
        val entity = tenantSaveDTO.id?.let {
            tenantRepository.findById(it).orElseThrow { BizErr(BizErrEnum.TENANT_NOT_FOUND) }
        } ?: tenantMapper.saveDtoToEntity(tenantSaveDTO)
        tenantRepository.save(entity)
        logInfo("保存租户成功|${entity.toJson()}")
        return tenantMapper.entityToVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除租户|ID：$id")
        tenantRepository.deleteById(id)
        return true
    }

}