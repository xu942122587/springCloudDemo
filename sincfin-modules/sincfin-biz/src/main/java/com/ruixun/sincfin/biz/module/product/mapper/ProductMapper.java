package com.ruixun.sincfin.biz.module.product.mapper;

import java.util.List;

import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.ProductIntroduceDto;
import com.ruixun.sincfin.domain.entity.ProductEntity;
import com.ruixun.sincfin.domain.query.ProductQuery;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
	
	
    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);

	List<ProductEntity> listByCondition(ProductQuery query);

    /**
     * 管理平台产品列表
     * @param query
     * @return
     */
    List<ProductDto> listManagerByCondition(ProductQuery query);

    /**
     * 管理平台产品搜索
     * @param query
     * @return
     */
    List<ProductDto> listSearch(ProductQuery query);

    ProductDto getDetailById(@Param(value = "id") Long id);

    int countByCondition(ProductQuery query);

    /**
     * 新用户的推荐标的
     * @return
     */
    ProductDto getNewUserRecommend();

	/**
	 * 老用户的推荐标的
	 * @return
	 */
    ProductDto getOldUserRecommend();
    
    /**
     * 根据类型的区域，查询标的
     * @return
     */
    List<ProductDto> listByTypeArea(String area);
    /**
     * 查询已售罄列表
     * @return
     */
	List<ProductDto> listSellOut();

	/**
	 * 查询可投资的标的
	 * @param productId
	 * @return
	 */
	ProductDto getAvailable(Long productId);
	
	/**
	 * 查询标的完整信息
	 * @param productId
	 * @return
	 */
	ProductDto getFullById(Long productId);

	/**
	 * 查询产品介绍
	 * @param id
	 * @return
	 */
	ProductIntroduceDto introduce(Long id);

	/**
	 * 购买产品后更新已售、未售金额数
	 * @param id
	 * @param soldAmount
	 * @param unsoldAmount
	 * @return
	 */
	int updateSoldAmount(@Param("id") Long id, @Param("soldAmount") Long soldAmount,
						 @Param("unsoldAmount") Long unsoldAmount);
	
	
}