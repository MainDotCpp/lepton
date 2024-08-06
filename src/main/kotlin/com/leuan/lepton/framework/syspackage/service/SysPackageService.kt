package com.leuan.lepton.framework.syspackage.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.menu.service.MenuService
import com.leuan.lepton.framework.syspackage.controller.dto.SysPackageQueryDTO
import com.leuan.lepton.framework.syspackage.controller.dto.SysPackageSaveDTO
import com.leuan.lepton.framework.syspackage.controller.vo.SysPackageVO
import com.leuan.lepton.framework.syspackage.dal.QSysPackage
import com.leuan.lepton.framework.syspackage.dal.SysPackage
import com.leuan.lepton.framework.syspackage.dal.SysPackageRepository
import com.leuan.lepton.framework.syspackage.mapping.SysPackageMapper
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
    private lateinit var menuService: MenuService

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qSysPackage = QSysPackage.sysPackage

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [SysPackageVO]
     */
    fun getById(id: Long): SysPackageVO {
        val entity = sysPackageRepository
            .findOne(qSysPackage.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        return sysPackageMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<SysPackageVO>]
     */
    fun list(queryDTO: SysPackageQueryDTO): List<SysPackageVO> {
        return jpaQueryFactory
            .selectFrom(qSysPackage)
            .buildExpressions(queryDTO)
            .fetch()
            .map(sysPackageMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<SysPackageVO>]
     */
    fun page(queryDTO: SysPackageQueryDTO): PageDTO<SysPackageVO> {
        val pageDTO = PageDTO<SysPackageVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qSysPackage)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qSysPackage.id.count()).from(qSysPackage).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(sysPackageMapper::toVO)
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

        sysPackageSaveDTO.menuIds = menuService.findAncestorsByMenuIds(sysPackageSaveDTO.menuIds ?: mutableSetOf())
        sysPackageMapper.partialUpdate(sysPackageSaveDTO, entity)
        sysPackageRepository.save(entity)
        return sysPackageMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        sysPackageRepository.deleteById(id)
        return true
    }

}