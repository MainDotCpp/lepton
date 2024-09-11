package com.leuan.lepton.framework.dept.mapping

import com.leuan.lepton.framework.common.mapping.LeptonBaseMapping
import com.leuan.lepton.framework.dept.controller.dto.DeptSaveDTO
import com.leuan.lepton.framework.dept.controller.dto.DeptQueryDTO
import com.leuan.lepton.framework.dept.controller.vo.DeptVO
import com.leuan.lepton.framework.dept.dal.Dept
import org.mapstruct.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = [LeptonBaseMapping::class]
)
abstract class DeptMapper {
    abstract fun toVO(dept: Dept): DeptVO

    @InheritConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract fun partialUpdate(saveDTO: DeptSaveDTO, @MappingTarget dept: Dept): Dept

    fun idToEntity(id: Long?): Dept? {
        return id?.let { Dept().apply { this.id = it } }
    }

    fun entityToId(dept: Dept?): Long? {
        return dept?.id
    }

}