package com.leuan.lepton.framework.dept.service

import cn.hutool.core.lang.tree.Tree
import cn.hutool.core.lang.tree.TreeNode
import cn.hutool.core.lang.tree.TreeNodeConfig
import cn.hutool.core.lang.tree.TreeUtil
import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.framework.dept.controller.dto.DeptQueryDTO
import com.leuan.lepton.framework.dept.controller.dto.DeptSaveDTO
import com.leuan.lepton.framework.dept.controller.vo.DeptVO
import com.leuan.lepton.framework.dept.dal.QDept
import com.leuan.lepton.framework.dept.dal.Dept
import com.leuan.lepton.framework.dept.dal.DeptRepository
import com.leuan.lepton.framework.dept.mapping.DeptMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 部门服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[DeptService]
 */
@Service
class DeptService {

    @Resource
    private lateinit var deptMapper: DeptMapper

    @Resource
    private lateinit var deptRepository: DeptRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qDept = QDept.dept

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [DeptVO]
     */
    fun getById(id: Long): DeptVO {
        val entity = deptRepository
            .findOne(qDept.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.DEPT_NOT_FOUND) }
        return deptMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<DeptVO>]
     */
    fun list(queryDTO: DeptQueryDTO): List<DeptVO> {
        return jpaQueryFactory
            .selectFrom(qDept)
            .buildExpressions(queryDTO)
            .fetch()
            .map(deptMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<DeptVO>]
     */
    fun page(queryDTO: DeptQueryDTO): PageDTO<DeptVO> {
        val pageDTO = PageDTO<DeptVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qDept)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qDept.id.count()).from(qDept).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(deptMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [deptSaveDTO] 部门保存传输层对象
     * @return [DeptVO]
     */
    fun save(deptSaveDTO: DeptSaveDTO): DeptVO {
        val entity = deptSaveDTO.id?.let {
            deptRepository.findById(it).orElseThrow { BizErr(BizErrEnum.DEPT_NOT_FOUND) }
        } ?: Dept()

        deptMapper.partialUpdate(deptSaveDTO, entity)
        deptRepository.save(entity)
        if (entity.parentId == 0L) {
            entity.code = "#${entity.id}"
            deptRepository.save(entity)
        } else {
            val parent = deptRepository.findById(entity.parentId)
                .orElseThrow { BizErr(BizErrEnum.DEPT_NOT_FOUND) }
            entity.code = "${parent.code}.${entity.id}"
            deptRepository.save(entity)
        }
        return deptMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        deptRepository.deleteById(id)
        return true
    }

    fun deptTree(): MutableList<Tree<Long>>? {
        val config = TreeNodeConfig()
        config.setWeightKey("sort")

        val deptList = deptRepository.findAll()
        val deptNodes = deptList.map {
            val node = TreeNode(it.id, it.parentId, it.name, it.sort)
            node
        }

        return TreeUtil.build(deptNodes, 0L, config) { t, tree ->
            tree.id = t.id
            tree.parentId = t.parentId
            tree.name = t.name
        }
    }

}