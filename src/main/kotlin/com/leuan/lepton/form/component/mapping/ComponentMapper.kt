package com.leuan.lepton.form.component.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.form.component.controller.dto.ComponentSaveDTO
import com.leuan.lepton.form.component.controller.vo.ComponentVO
import com.leuan.lepton.form.component.dal.Component
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class ComponentMapper {
    abstract fun toVO(component: Component): ComponentVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: ComponentSaveDTO, @MappingTarget component: Component): Component

    fun idToEntity(id: Long?): Component? {
        return id?.let { Component().apply { this.id = it } }
    }

    fun entityToId(component: Component?): Long? {
        return component?.id
    }

}