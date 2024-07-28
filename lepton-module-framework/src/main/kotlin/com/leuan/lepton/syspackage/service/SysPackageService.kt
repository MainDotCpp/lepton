package com.leuan.lepton.syspackage.service

import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.common.constants.BizErrEnum
import com.leuan.lepton.syspackage.controller.dto.SysPackageQueryDTO
import com.leuan.lepton.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.syspackage.controller.vo.SysPackageVO
import com.leuan.lepton.syspackage.dal.QSysPackage
import com.leuan.lepton.syspackage.dal.SysPackage
import com.leuan.lepton.syspackage.dal.SysPackageRepository
import com.leuan.lepton.syspackage.mapping.SysPackageMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 系统套餐服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[SysPackageService]
 */
@Service
class SysPackageService {

    @Resource
    private lateinit var sysPackageMapper: SysPackageMapper

    @Resource
    private lateinit var sysPackageRepository: SysPackageRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    /**
     * 通过id获取
     * @param [id] ID
     * @return [SysPackageVO]
     */
    fun getById(id: Long): SysPackageVO {
        val entity = sysPackageRepository.findById(id).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        return sysPackageMapper.entityToVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: SysPackageQueryDTO) = arrayOf(
        queryDTO.id?.let { QSysPackage.sysPackage.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<SysPackageVO>]
     */
    fun list(queryDTO: SysPackageQueryDTO): List<SysPackageVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(QSysPackage.sysPackage)
            .where(*expressions)
            .fetch()
            .map(sysPackageMapper::entityToVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<SysPackageVO>]
     */
    fun page(queryDTO: SysPackageQueryDTO): PageDTO<SysPackageVO> {
        val pageDTO = PageDTO<SysPackageVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(QSysPackage.sysPackage)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(QSysPackage.sysPackage.id.count()).from(QSysPackage.sysPackage).where(*expressions)
                .fetchOne() ?: 0
        pageDTO.records = query.fetch().map(sysPackageMapper::entityToVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [sysPackageSaveDTO] 系统套餐保存传输层对象
     * @return [SysPackageVO]
     */
    fun save(sysPackageSaveDTO: SysPackageSaveDTO): SysPackageVO {
        val entity = sysPackageSaveDTO.id?.let {
            sysPackageRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: SysPackage()

        sysPackageMapper.partialUpdate(sysPackageSaveDTO, entity)
        sysPackageRepository.save(entity)
        return sysPackageMapper.entityToVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除系统套餐|ID：$id")
        sysPackageRepository.deleteById(id)
        return true
    }

}