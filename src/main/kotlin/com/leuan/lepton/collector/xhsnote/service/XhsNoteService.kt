package com.leuan.lepton.collector.xhsnote.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.collector.xhsnote.controller.dto.XhsNoteQueryDTO
import com.leuan.lepton.collector.xhsnote.controller.dto.XhsNoteSaveDTO
import com.leuan.lepton.collector.xhsnote.controller.vo.XhsNoteVO
import com.leuan.lepton.collector.xhsnote.dal.QXhsNote
import com.leuan.lepton.collector.xhsnote.dal.XhsNote
import com.leuan.lepton.collector.xhsnote.dal.XhsNoteRepository
import com.leuan.lepton.collector.xhsnote.mapping.XhsNoteMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 小红书笔记服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[XhsNoteService]
 */
@Service
class XhsNoteService {

    @Resource
    private lateinit var xhsNoteMapper: XhsNoteMapper

    @Resource
    private lateinit var xhsNoteRepository: XhsNoteRepository

    @Resource(name = "collectorJpaQueryFactory")
    private lateinit var collectorJpaQueryFactory: JPAQueryFactory

    private val qXhsNote = QXhsNote.xhsNote

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [XhsNoteVO]
     */
    fun getById(id: Long): XhsNoteVO {
        val entity = xhsNoteRepository
            .findOne(qXhsNote.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.NOT_LOGIN) }
        return xhsNoteMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<XhsNoteVO>]
     */
    fun list(queryDTO: XhsNoteQueryDTO): List<XhsNoteVO> {
        return collectorJpaQueryFactory
            .selectFrom(qXhsNote)
            .buildExpressions(queryDTO)
            .fetch()
            .map(xhsNoteMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<XhsNoteVO>]
     */
    fun page(queryDTO: XhsNoteQueryDTO): PageDTO<XhsNoteVO> {
        val pageDTO = PageDTO<XhsNoteVO>(queryDTO)
        val query = collectorJpaQueryFactory
            .selectFrom(qXhsNote)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            collectorJpaQueryFactory.select(qXhsNote.id.count()).from(qXhsNote).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(xhsNoteMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [xhsNoteSaveDTO] 小红书笔记保存传输层对象
     * @return [XhsNoteVO]
     */
    fun save(xhsNoteSaveDTO: XhsNoteSaveDTO): XhsNoteVO {
        val entity = xhsNoteSaveDTO.id?.let {
            xhsNoteRepository.findById(it).orElseThrow { BizErr(BizErrEnum.NOT_LOGIN) }
        } ?: XhsNote()

        xhsNoteMapper.partialUpdate(xhsNoteSaveDTO, entity)
        xhsNoteRepository.save(entity)
        return xhsNoteMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        xhsNoteRepository.deleteById(id)
        return true
    }

}