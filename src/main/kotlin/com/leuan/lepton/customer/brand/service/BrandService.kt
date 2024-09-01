package com.leuan.lepton.customer.brand.service

import com.leuan.lepton.framework.common.constants.BizErrEnum
import com.leuan.lepton.framework.common.exception.BizErr
import com.leuan.lepton.framework.common.http.PageDTO
import com.leuan.lepton.framework.common.utils.buildExpressions
import com.leuan.lepton.customer.brand.controller.dto.BrandQueryDTO
import com.leuan.lepton.customer.brand.controller.dto.BrandSaveDTO
import com.leuan.lepton.customer.brand.controller.vo.BrandVO
import com.leuan.lepton.customer.brand.dal.QBrand
import com.leuan.lepton.customer.brand.dal.Brand
import com.leuan.lepton.customer.brand.dal.BrandRepository
import com.leuan.lepton.customer.brand.mapping.BrandMapper
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.Resource
import org.springframework.stereotype.Service

/**
 * 品牌服务
 * @author yangyang
 * @date 2024/07/26
 * @constructor 创建[BrandService]
 */
@Service
class BrandService {

    @Resource
    private lateinit var brandMapper: BrandMapper

    @Resource
    private lateinit var brandRepository: BrandRepository

    @Resource
    private lateinit var jpaQueryFactory: JPAQueryFactory

    private val qBrand = QBrand.brand

    /**
     * 通过id获取套餐
     * @param [id] ID
     * @return [BrandVO]
     */
    fun getById(id: Long): BrandVO {
        val entity = brandRepository
            .findOne(qBrand.id.eq(id))
            .orElseThrow { BizErr(BizErrEnum.BRAND_NOT_FOUND) }
        return brandMapper.toVO(entity)
    }


    /**
     * 列表
     * @param [queryDTO] 查询传输层对象
     * @return [List<BrandVO>]
     */
    fun list(queryDTO: BrandQueryDTO): List<BrandVO> {
        return jpaQueryFactory
            .selectFrom(qBrand)
            .buildExpressions(queryDTO)
            .fetch()
            .map(brandMapper::toVO)
    }

    /**
     * 分页查询套餐
     * @param [queryDTO] 查询传输层对象
     * @return [PageDTO<BrandVO>]
     */
    fun page(queryDTO: BrandQueryDTO): PageDTO<BrandVO> {
        val pageDTO = PageDTO<BrandVO>(queryDTO)
        val query = jpaQueryFactory
            .selectFrom(qBrand)
            .buildExpressions(queryDTO)
            .offset(pageDTO.offset)
            .limit(pageDTO.pageSize)
        pageDTO.total =
            jpaQueryFactory.select(qBrand.id.count()).from(qBrand).buildExpressions(queryDTO, sort = false)
                .fetchOne()!!
        pageDTO.data = query.fetch().map(brandMapper::toVO)
        return pageDTO
    }

    /**
     * 保存
     * @param [brandSaveDTO] 品牌保存传输层对象
     * @return [BrandVO]
     */
    fun save(brandSaveDTO: BrandSaveDTO): BrandVO {
        val entity = brandSaveDTO.id?.let {
            brandRepository.findById(it).orElseThrow { BizErr(BizErrEnum.BRAND_NOT_FOUND) }
        } ?: Brand()

        brandMapper.partialUpdate(brandSaveDTO, entity)
        brandRepository.save(entity)
        return brandMapper.toVO(entity)
    }

    /**
     * 按id删除
     * @param [id] ID
     * @return [Boolean]
     */
    fun deleteById(id: Long): Boolean {
        brandRepository.deleteById(id)
        return true
    }

}