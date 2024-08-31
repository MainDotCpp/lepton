package com.leuan.lepton.order.goods.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.order.goods.controller.dto.GoodsQueryDTO
import com.leuan.lepton.order.goods.controller.dto.GoodsSaveDTO
import com.leuan.lepton.order.goods.controller.vo.GoodsVO
import com.leuan.lepton.order.goods.dal.QGoods
import com.leuan.lepton.order.goods.dal.Goods
import com.leuan.lepton.order.goods.dal.GoodsRepository
import com.leuan.lepton.order.goods.mapping.GoodsMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 商品服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[GoodsService]
 */
@Service
class GoodsService {

    @Resource
    private lateinit var goodsMapper: GoodsMapper

    @Resource
    private lateinit var goodsRepository: GoodsRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qGoods = QGoods.goods

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [GoodsVO]
     */
    fun getById(id: Long): GoodsVO {
        val entity = goodsRepository
            .findOne(qGoods.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.GOODS_NOT_FOUND) }
        return goodsMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<GoodsVO>]
     */
    fun list(queryDTO: GoodsQueryDTO): List<GoodsVO> {
        return jpaQueryFactory
            .selectFrom(qGoods)
            .buildExpressions(queryDTO)
            .fetch()
            .map(goodsMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<GoodsVO>]
     */
    fun page(queryDTO: GoodsQueryDTO): PageDTO<GoodsVO> {
        val pageDTO = PageDTO<GoodsVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qGoods)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qGoods.id.count()).from(qGoods).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(goodsMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [goodsSaveDTO] 商品保存传输层对象
     * @return [GoodsVO]
     */
    fun save(goodsSaveDTO: GoodsSaveDTO): GoodsVO {
        val entity = goodsSaveDTO.id?.let {
            goodsRepository.findById(it).orElseThrow { BizErr(BizErrEnum.GOODS_NOT_FOUND) }
        } ?: Goods()

        goodsMapper.partialUpdate(goodsSaveDTO, entity)
        goodsRepository.save(entity)
        return goodsMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        goodsRepository.deleteById(id)
        return true
    }

}