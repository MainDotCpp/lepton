package com.leuan.lepton.framework.dict.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.dict.controller.dto.DictQueryDTO
import com.leuan.lepton.framework.dict.controller.dto.DictSaveDTO
import com.leuan.lepton.framework.dict.controller.vo.DictVO
import com.leuan.lepton.framework.dict.dal.Dict
import com.leuan.lepton.framework.dict.dal.DictRepository
import com.leuan.lepton.framework.dict.dal.QDict
import com.leuan.lepton.framework.dict.dal.QDictItem
import com.leuan.lepton.framework.dict.mapping.DictMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 字典服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[DictService]
 */
@Service
class DictService {

    @Resource
    private lateinit var dictMapper: DictMapper

    @Resource
    private lateinit var dictRepository: DictRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qDict = QDict.dict

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [DictVO]
     */
    fun getById(id: Long): DictVO {
        val entity = dictRepository
            .findOne(qDict.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.DICT_NOT_FOUND) }
        return dictMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<DictVO>]
     */
    fun list(queryDTO: DictQueryDTO): List<DictVO> {
        val qDictItem = QDictItem.dictItem
        return jpaQueryFactory
            .selectFrom(qDict)
            .join(qDict.items,qDictItem)
            .fetchJoin()
            .buildExpressions(queryDTO, sort = false)
            .orderBy(qDictItem.sort.asc())
            .fetch()
            .map(dictMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<DictVO>]
     */
    fun page(queryDTO: DictQueryDTO): PageDTO<DictVO> {
        val pageDTO = PageDTO<DictVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qDict)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qDict.id.count()).from(qDict).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(dictMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [dictSaveDTO] 字典保存传输层对象
     * @return [DictVO]
     */
    fun save(dictSaveDTO: DictSaveDTO): DictVO {
        val entity = dictSaveDTO.id?.let {
            dictRepository.findById(it).orElseThrow { BizErr(BizErrEnum.DICT_NOT_FOUND) }
        } ?: Dict()

        dictMapper.partialUpdate(dictSaveDTO, entity)
        dictRepository.save(entity)
        return dictMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        dictRepository.deleteById(id)
        return true
    }

}