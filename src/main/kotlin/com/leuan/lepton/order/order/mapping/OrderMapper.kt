package com.leuan.lepton.order.order.mapping

import com.leuan.lepton.customer.customer.mapping.CustomerMapper
import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.order.goods.mapping.GoodsMapper
import com.leuan.lepton.order.order.controller.dto.OrderQueryDTO
import com.leuan.lepton.order.order.controller.dto.OrderSaveDTO
import com.leuan.lepton.order.order.controller.vo.OrderVO
import com.leuan.lepton.order.order.dal.Order
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class, CustomerMapper::class, GoodsMapper::class]
)
abstract class OrderMapper {
    @org.mapstruct.Mapping(source = "sellerId", target = "seller.id")
    abstract fun toEntity(orderVO: OrderVO): Order
    abstract fun toEntity(orderQueryDTO: OrderQueryDTO): Order

    @org.mapstruct.Mapping(source = "seller.id", target = "sellerId")
    abstract fun toVO(order: Order): OrderVO

    @Mapping(source = "sellerId", target = "seller")
    @Mapping(source = "customerId", target = "customer")
    @Mapping(source = "goodsId", target = "goods")
    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: OrderSaveDTO, @MappingTarget order: Order): Order

    fun idToEntity(id: Long?): Order? {
        return id?.let { Order().apply { this.id = it } }
    }

    fun entityToId(order: Order?): Long? {
        return order?.id
    }

}