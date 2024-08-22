package com.leuan.lepton.framework.dict.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.dict.controller.dto.DictSaveDTO
import com.leuan.lepton.framework.dict.controller.dto.DictQueryDTO
import com.leuan.lepton.framework.dict.controller.vo.DictVO
import com.leuan.lepton.framework.dict.dal.Dict
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class DictMapper {
    abstract fun toEntity(dictVO: DictVO): Dict
    abstract fun toEntity(dictQueryDTO: DictQueryDTO): Dict
    abstract fun toVO(dict: Dict): DictVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: DictSaveDTO, @MappingTarget dict: Dict): Dict

    fun idToEntity(id: Long?): Dict? {
        return id?.let { Dict().apply { this.id = it } }
    }

    fun entityToId(dict: Dict?): Long? {
        return dict?.id
    }

}