package com.leuan.lepton.customer.channel.controller.vo

import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * DTO for {@link com.leuan.lepton.customer.channel.dal.Channel }
 */
@NoArgsConstructor
data class ChannelVO(
    var id: Long
) : Serializable