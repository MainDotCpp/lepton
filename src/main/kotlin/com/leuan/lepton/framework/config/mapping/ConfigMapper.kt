package com.leuan.lepton.framework.config.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.config.controller.dto.ConfigQueryDTO
import com.leuan.lepton.framework.config.controller.dto.ConfigSaveDTO
import com.leuan.lepton.framework.config.controller.vo.ConfigVO
import com.leuan.lepton.framework.config.dal.Config
import com.leuan.lepton.framework.config.dal.TenantConfig
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class ConfigMapper {
    abstract fun toEntity(configVO: ConfigVO): Config
    abstract fun toEntity(configQueryDTO: ConfigQueryDTO): Config
    abstract fun toVO(config: Config): ConfigVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: ConfigSaveDTO, @MappingTarget config: Config): Config

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: TenantConfig, @MappingTarget config: TenantConfig): TenantConfig

    fun idToEntity(id: Long?): Config? {
        return id?.let { Config().apply { this.id = it } }
    }

    fun entityToId(config: Config?): Long? {
        return config?.id
    }

}