package com.leuan.lepton.customer.channel.controller.dto

import lombok.NoArgsConstructor

/**
 * DTO for {@link com.leuan.lepton.customer.channel.dal.Channel }
 */
@NoArgsConstructor
data class ChannelSaveDTO(
    val id: Long? = null, val name: String = "", val icon: String? = null, val textColor: String? = null, val backgroundColor: String? = null
)
