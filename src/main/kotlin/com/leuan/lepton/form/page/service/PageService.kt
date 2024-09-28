package com.leuan.lepton.form.page.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.form.page.controller.dto.PageQueryDTO
import com.leuan.lepton.form.page.controller.dto.PageSaveDTO
import com.leuan.lepton.form.page.controller.vo.PageVO
import com.leuan.lepton.form.page.dal.QPage
import com.leuan.lepton.form.page.dal.Page
import com.leuan.lepton.form.page.dal.PageRepository
import com.leuan.lepton.form.page.mapping.PageMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 页面服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[PageService]
 */
@Service
class PageService {

    @Resource
    private lateinit var pageMapper: PageMapper

    @Resource
    private lateinit var pageRepository: PageRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qPage = QPage.page

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [PageVO]
     */
    fun getById(id: Long): PageVO {
        val entity = pageRepository
            .findOne(qPage.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.PAGE_NOT_FOUND) }
        return pageMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<PageVO>]
     */
    fun list(queryDTO: PageQueryDTO): List<PageVO> {
        return jpaQueryFactory
            .selectFrom(qPage)
            .buildExpressions(queryDTO)
            .fetch()
            .map(pageMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<PageVO>]
     */
    fun page(queryDTO: PageQueryDTO): PageDTO<PageVO> {
        val pageDTO = PageDTO<PageVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qPage)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qPage.id.count()).from(qPage).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(pageMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [pageSaveDTO] 页面保存传输层对象
     * @return [PageVO]
     */
    fun save(pageSaveDTO: PageSaveDTO): PageVO {
        val entity = pageSaveDTO.id?.let {
            pageRepository.findById(it).orElseThrow { BizErr(BizErrEnum.PAGE_NOT_FOUND) }
        } ?: Page()

        pageMapper.partialUpdate(pageSaveDTO, entity)
        pageRepository.save(entity)
        return pageMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        pageRepository.deleteById(id)
        return true
    }

}