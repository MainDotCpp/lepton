package com.leuan.lepton.menu.service

import cn.hutool.core.lang.tree.Tree
import cn.hutool.core.lang.tree.TreeNode
import cn.hutool.core.lang.tree.TreeNodeConfig
import cn.hutool.core.lang.tree.TreeUtil
import cn.hutool.core.lang.tree.parser.NodeParser
import com.leuan.lepton.common.exception.BizErr
import com.leuan.lepton.common.http.PageDTO
import com.leuan.lepton.common.log.logInfo
import com.leuan.lepton.common.utils.toJson
import com.leuan.lepton.constants.BizErrEnum
import com.leuan.lepton.menu.controller.dto.MenuQueryDTO
import com.leuan.lepton.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.menu.controller.vo.MenuVO
import com.leuan.lepton.menu.dal.QMenu
import com.leuan.lepton.menu.dal.MenuRepository
import com.leuan.lepton.menu.mapping.MenuMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 系统菜单服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[MenuService]
 */
@Service
class MenuService {

    @Resource
    private lateinit var menuMapper: MenuMapper

    @Resource
    private lateinit var menuRepository: MenuRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    /**
     * 通过id获取
     * @param [id] ID
     * @return [MenuVO]
     */
    fun getById(id: Long): MenuVO {
        val entity = menuRepository.findById(id).orElseThrow { BizErr(BizErrEnum.MENU_NOT_FOUND) }
        return menuMapper.entityToVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 询问传输层对象
     */
    private fun buildExpressions(queryDTO: MenuQueryDTO) = arrayOf(
        queryDTO.id?.let { QMenu.menu.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 询问传输层对象
     * @return [List<MenuVO>]
     */
    fun list(queryDTO: MenuQueryDTO): List<MenuVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(QMenu.menu)
            .where(*expressions)
            .fetch()
            .map(menuMapper::entityToVO)
    }

    /**
     * 页
     * @param [queryDTO] 询问传输层对象
     * @return [PageDTO<MenuVO>]
     */
    fun page(queryDTO: MenuQueryDTO): PageDTO<MenuVO> {
        val pageDTO = PageDTO<MenuVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(QMenu.menu)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(QMenu.menu.id.count()).from(QMenu.menu).where(*expressions).fetchOne() ?: 0
        pageDTO.records = query.fetch().map(menuMapper::entityToVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [menuSaveDTO] 系统菜单保存传输层对象
     * @return [MenuVO]
     */
    fun save(menuSaveDTO: MenuSaveDTO): MenuVO {
        val entity = menuSaveDTO.id?.let {
            menuRepository.findById(it).orElseThrow { BizErr(BizErrEnum.MENU_NOT_FOUND) }
        } ?: menuMapper.saveDtoToEntity(menuSaveDTO)
        menuRepository.save(entity)
        logInfo("保存系统菜单成功|${entity.toJson()}")
        return menuMapper.entityToVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        logInfo("删除系统菜单|ID：$id")
        menuRepository.deleteById(id)
        return true
    }

    fun getMenuTree(): Tree<Long> {
        val menus = menuRepository.findAll() // TODO: 替换为只查找当前租户套餐内的菜单

        val config = TreeNodeConfig()
        config.weightKey = "sort"
        val treeNodes = menus.map {
            val node = TreeNode<Long>(it.id, it.parentId, it.name, it.sort)
            node.setExtra(
                mapOf(
                    "type" to it.type,
                    "path" to it.path,
                    "icon" to it.icon,
                    "component" to it.component,
                    "permission" to it.permission,
                    "hidden" to it.hidden,
                    "cache" to it.cache,
                    "sort" to it.sort
                )
            )
            node
        }
        return TreeUtil.buildSingle(treeNodes, 0L)
    }

}