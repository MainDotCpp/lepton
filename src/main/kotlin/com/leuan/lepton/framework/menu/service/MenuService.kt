package com.leuan.lepton.framework.menu.service

import cn.hutool.core.lang.tree.Tree
import cn.hutool.core.lang.tree.TreeNode
import cn.hutool.core.lang.tree.TreeNodeConfig
import cn.hutool.core.lang.tree.TreeUtil
import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.log.logInfo
import com.leuan.lepton.framework.common.thread.getThreadContext
import com.leuan.lepton.framework.common.utils.toJson
import com.leuan.lepton.framework.menu.controller.dto.MenuQueryDTO
import com.leuan.lepton.framework.menu.controller.dto.MenuSaveDTO
import com.leuan.lepton.framework.menu.controller.vo.MenuVO
import com.leuan.lepton.framework.menu.dal.Menu
import com.leuan.lepton.framework.menu.dal.MenuRepository
import com.leuan.lepton.framework.menu.dal.QMenu
import com.leuan.lepton.framework.menu.mapping.MenuMapper
import com.leuan.lepton.framework.user.service.UserService
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 菜单服务
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
    private lateinit var userService: UserService

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qMenu = QMenu.menu

    /**
     * 通过id获取菜单
     * @param [id] ID
     * @return [MenuVO]
     */
    fun getById(id: Long): MenuVO {
        val entity = menuRepository
            .findOne(qMenu.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        return menuMapper.toVO(entity)
    }

    /**
     * 构建表达式
     * @param [queryDTO] 查询传输层对象
     */
    private fun buildExpressions(queryDTO: MenuQueryDTO) = arrayOf(
        queryDTO.id?.let { qMenu.id.eq(it) },
    )

    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<MenuVO>]
     */
    fun list(queryDTO: MenuQueryDTO): List<MenuVO> {
        val expressions = buildExpressions(queryDTO)
        return jpaQueryFactory
            .selectFrom(qMenu)
            .where(*expressions)
            .fetch()
            .map(menuMapper::toVO)
    }

    /**
     * 分页查询菜单
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<MenuVO>]
     */
    fun page(queryDTO: MenuQueryDTO): PageDTO<MenuVO> {
        val pageDTO = PageDTO<MenuVO>(queryDTO)
        val expressions = buildExpressions(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qMenu)
            .where(*expressions)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qMenu.id.count()).from(qMenu).where(*expressions)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(menuMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [menuSaveDTO] 菜单保存传输层对象
     * @return [MenuVO]
     */
    fun save(menuSaveDTO: MenuSaveDTO): MenuVO {
        val entity = menuSaveDTO.id?.let {
            menuRepository.findById(it).orElseThrow { BizErr(BizErrEnum.SYS_PACKAGE_NOT_FOUND) }
        } ?: Menu()

        menuMapper.partialUpdate(menuSaveDTO, entity)
        menuRepository.save(entity)
        return menuMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        menuRepository.deleteById(id)
        return true
    }

    fun getMenuTree(): MutableList<Tree<Long>>? {
        val userInfo = userService.getUserInfo(tenantId = getThreadContext().tenantId)
        val menus = jpaQueryFactory.selectFrom(QMenu.menu)
            .where(
                QMenu.menu.hidden.eq(false),
                if (userInfo.roles.contains("super_admin")) null else QMenu.menu.permission.`in`(userInfo.permissions)
            )
            .orderBy(QMenu.menu.id.asc())
            .fetch()
        return getMenuTree(menus)
    }

    fun getMenuTree(menus: Collection<Menu>): MutableList<Tree<Long>>? {
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
        logInfo("菜单树：${treeNodes.toJson()}")
        return TreeUtil.build(treeNodes, 0L, config) { t, tree ->
            tree.id = t.id
            tree.parentId = t.parentId
            tree.name = t.name
            tree.putExtra("sort", t.weight)
            tree.putExtra("path", t.extra["path"])
            tree.putExtra("icon", t.extra["icon"])
        }
    }

    fun findAncestorsByMenuIds(menuIds: MutableCollection<Long> = mutableSetOf()): MutableSet<Long> {
        logInfo("查找菜单祖先：$menuIds")
        val menuTree = this.getMenuTree()
        val ancestors = mutableSetOf<Long>()

        menuIds.forEach { menuId ->
            menuTree?.forEach { node ->
                val ancestorsByMenuId = findParentId(node, menuId)
                ancestors.addAll(ancestorsByMenuId)
            }
        }

        logInfo("查找菜单祖先结果：$ancestors")
        return ancestors
    }


    fun findParentId(node: Tree<Long>, menuId: Long): List<Long> {
        val ancestors = mutableListOf<Long>()
        // 终止条件
        val children = node.children
        if (node.id == menuId) {
            ancestors.add(node.id)
            return ancestors
        }

        children?.forEach { child ->
            val ancestorsByMenuId = findParentId(child, menuId)
            if (ancestorsByMenuId.isNotEmpty()) {
                ancestors.add(node.id)
                ancestors.addAll(ancestorsByMenuId)
                return@forEach
            }
        }

        return ancestors
    }

    fun findParentId(node: Tree<Long>): List<Long> {
        val ancestors = mutableListOf<Long>()
        val parent = node.parent
        if (parent != null) {
            ancestors.add(parent.id)
            ancestors.addAll(findParentId(parent))
        }
        return ancestors

    }

}