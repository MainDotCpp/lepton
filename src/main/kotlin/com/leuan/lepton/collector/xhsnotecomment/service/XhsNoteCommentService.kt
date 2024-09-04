package com.leuan.lepton.collector.xhsnotecomment.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.collector.xhsnotecomment.controller.dto.XhsNoteCommentQueryDTO
import com.leuan.lepton.collector.xhsnotecomment.controller.dto.XhsNoteCommentSaveDTO
import com.leuan.lepton.collector.xhsnotecomment.controller.vo.XhsNoteCommentVO
import com.leuan.lepton.collector.xhsnotecomment.dal.QXhsNoteComment
import com.leuan.lepton.collector.xhsnotecomment.dal.XhsNoteComment
import com.leuan.lepton.collector.xhsnotecomment.dal.XhsNoteCommentRepository
import com.leuan.lepton.collector.xhsnotecomment.mapping.XhsNoteCommentMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 小红书评论服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[XhsNoteCommentService]
 */
@Service
class XhsNoteCommentService {

    @Resource
    private lateinit var xhsNoteCommentMapper: XhsNoteCommentMapper

    @Resource
    private lateinit var xhsNoteCommentRepository: XhsNoteCommentRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qXhsNoteComment = QXhsNoteComment.xhsNoteComment

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [XhsNoteCommentVO]
     */
    fun getById(id: Long): XhsNoteCommentVO {
        val entity = xhsNoteCommentRepository
            .findOne(qXhsNoteComment.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.XHS_NOTE_COMMENT_NOT_FOUND) }
        return xhsNoteCommentMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<XhsNoteCommentVO>]
     */
    fun list(queryDTO: XhsNoteCommentQueryDTO): List<XhsNoteCommentVO> {
        return jpaQueryFactory
            .selectFrom(qXhsNoteComment)
            .buildExpressions(queryDTO)
            .fetch()
            .map(xhsNoteCommentMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<XhsNoteCommentVO>]
     */
    fun page(queryDTO: XhsNoteCommentQueryDTO): PageDTO<XhsNoteCommentVO> {
        val pageDTO = PageDTO<XhsNoteCommentVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qXhsNoteComment)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qXhsNoteComment.id.count()).from(qXhsNoteComment).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(xhsNoteCommentMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [xhsNoteCommentSaveDTO] 小红书评论保存传输层对象
     * @return [XhsNoteCommentVO]
     */
    fun save(xhsNoteCommentSaveDTO: XhsNoteCommentSaveDTO): XhsNoteCommentVO {
        val entity = xhsNoteCommentSaveDTO.id?.let {
            xhsNoteCommentRepository.findById(it).orElseThrow { BizErr(BizErrEnum.XHS_NOTE_COMMENT_NOT_FOUND) }
        } ?: XhsNoteComment()

        xhsNoteCommentMapper.partialUpdate(xhsNoteCommentSaveDTO, entity)
        xhsNoteCommentRepository.save(entity)
        return xhsNoteCommentMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        xhsNoteCommentRepository.deleteById(id)
        return true
    }

}