package com.leuan.lepton.customer.channel.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.customer.channel.controller.dto.ChannelSaveDTO
import com.leuan.lepton.customer.channel.controller.dto.ChannelQueryDTO
import com.leuan.lepton.customer.channel.controller.vo.ChannelVO
import com.leuan.lepton.customer.channel.dal.Channel
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class ChannelMapper {
    abstract fun toEntity(channelVO: ChannelVO): Channel
    abstract fun toEntity(channelQueryDTO: ChannelQueryDTO): Channel
    abstract fun toVO(channel: Channel): ChannelVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: ChannelSaveDTO, @MappingTarget channel: Channel): Channel

    fun idToEntity(id: Long?): Channel? {
        return id?.let { Channel().apply { this.id = it } }
    }

    fun entityToId(channel: Channel?): Long? {
        return channel?.id
    }

}