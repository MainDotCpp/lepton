package com.leuan.lepton.customer.channel.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.customer.channel.controller.dto.ChannelQueryDTO
import com.leuan.lepton.customer.channel.controller.dto.ChannelSaveDTO
import com.leuan.lepton.customer.channel.controller.vo.ChannelVO
import com.leuan.lepton.customer.channel.dal.QChannel
import com.leuan.lepton.customer.channel.dal.Channel
import com.leuan.lepton.customer.channel.dal.ChannelRepository
import com.leuan.lepton.customer.channel.mapping.ChannelMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 客资渠道服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[ChannelService]
 */
@Service
class ChannelService {

    @Resource
    private lateinit var channelMapper: ChannelMapper

    @Resource
    private lateinit var channelRepository: ChannelRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qChannel = QChannel.channel

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [ChannelVO]
     */
    fun getById(id: Long): ChannelVO {
        val entity = channelRepository
            .findOne(qChannel.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.CHANNEL_NOT_FOUND) }
        return channelMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<ChannelVO>]
     */
    fun list(queryDTO: ChannelQueryDTO): List<ChannelVO> {
        return jpaQueryFactory
            .selectFrom(qChannel)
            .buildExpressions(queryDTO)
            .fetch()
            .map(channelMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<ChannelVO>]
     */
    fun page(queryDTO: ChannelQueryDTO): PageDTO<ChannelVO> {
        val pageDTO = PageDTO<ChannelVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qChannel)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qChannel.id.count()).from(qChannel).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(channelMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [channelSaveDTO] 客资渠道保存传输层对象
     * @return [ChannelVO]
     */
    fun save(channelSaveDTO: ChannelSaveDTO): ChannelVO {
        val entity = channelSaveDTO.id?.let {
            channelRepository.findById(it).orElseThrow { BizErr(BizErrEnum.CHANNEL_NOT_FOUND) }
        } ?: Channel()

        channelMapper.partialUpdate(channelSaveDTO, entity)
        channelRepository.save(entity)
        return channelMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        channelRepository.deleteById(id)
        return true
    }

}