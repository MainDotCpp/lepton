package com.leuan.lepton.order.goods.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.order.goods.controller.dto.GoodsSaveDTO
import com.leuan.lepton.order.goods.controller.dto.GoodsQueryDTO
import com.leuan.lepton.order.goods.controller.vo.GoodsVO
import com.leuan.lepton.order.goods.dal.Goods
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class GoodsMapper {
    abstract fun toEntity(goodsVO: GoodsVO): Goods
    abstract fun toEntity(goodsQueryDTO: GoodsQueryDTO): Goods
    abstract fun toVO(goods: Goods): GoodsVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: GoodsSaveDTO, @MappingTarget goods: Goods): Goods

    fun idToEntity(id: Long?): Goods? {
        return id?.let { Goods().apply { this.id = it } }
    }

    fun entityToId(goods: Goods?): Long? {
        return goods?.id
    }

}