package com.leuan.lepton.form.component.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.form.component.controller.dto.ComponentQueryDTO
import com.leuan.lepton.form.component.controller.dto.ComponentSaveDTO
import com.leuan.lepton.form.component.controller.vo.ComponentVO
import com.leuan.lepton.form.component.dal.QComponent
import com.leuan.lepton.form.component.dal.Component
import com.leuan.lepton.form.component.dal.ComponentRepository
import com.leuan.lepton.form.component.mapping.ComponentMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 表单组件服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[ComponentService]
 */
@Service
class ComponentService {

    @Resource
    private lateinit var componentMapper: ComponentMapper

    @Resource
    private lateinit var componentRepository: ComponentRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qComponent = QComponent.component

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [ComponentVO]
     */
    fun getById(id: Long): ComponentVO {
        val entity = componentRepository
            .findOne(qComponent.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.COMPONENT_NOT_FOUND) }
        return componentMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<ComponentVO>]
     */
    fun list(queryDTO: ComponentQueryDTO): List<ComponentVO> {
        return jpaQueryFactory
            .selectFrom(qComponent)
            .buildExpressions(queryDTO)
            .fetch()
            .map(componentMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<ComponentVO>]
     */
    fun page(queryDTO: ComponentQueryDTO): PageDTO<ComponentVO> {
        val pageDTO = PageDTO<ComponentVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qComponent)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qComponent.id.count()).from(qComponent).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(componentMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [componentSaveDTO] 表单组件保存传输层对象
     * @return [ComponentVO]
     */
    fun save(componentSaveDTO: ComponentSaveDTO): ComponentVO {
        val entity = componentSaveDTO.id?.let {
            componentRepository.findById(it).orElseThrow { BizErr(BizErrEnum.COMPONENT_NOT_FOUND) }
        } ?: Component()

        componentMapper.partialUpdate(componentSaveDTO, entity)
        componentRepository.save(entity)
        return componentMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        componentRepository.deleteById(id)
        return true
    }

}