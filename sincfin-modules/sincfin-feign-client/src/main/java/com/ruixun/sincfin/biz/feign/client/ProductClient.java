package com.ruixun.sincfin.biz.feign.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.dto.ProductCategoryDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.ProductIntroduceDto;
import com.ruixun.sincfin.domain.dto.ProductViewDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.OrderQuery;
import com.ruixun.sincfin.domain.query.ProductQuery;

@FeignClient("${biz.feign.name}")
public interface ProductClient {
	
	/**
	 * 添加
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/product/add",method = RequestMethod.POST)
	public ProductDto add(@RequestBody ProductDto dto);
	/**
	 * 删除
	 * @param accountDto
	 */
	@RequestMapping(value="/product/delete",method = RequestMethod.DELETE)
	public void delete(@RequestBody ProductDto productDto);
	
	/**
	 * 更新
	 * @param dto
	 * @param dto id
	 * @return
	 */
	@RequestMapping(value="/product/update", method = RequestMethod.POST)
	public int update(@RequestBody ProductDto dto) ;

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/product/getById",method = RequestMethod.GET)
	public ProductDto getById(@RequestParam("id") Long id);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/product/getDetailById",method = RequestMethod.GET)
	public ProductDto getDetailById(@RequestParam("id") Long id);

	/**
	 * 管理平台产品分页列表
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/product/listPageByCondition",method = RequestMethod.POST)
    public Pagination<ProductDto> listPageByCondition(@RequestBody ProductQuery query);

	/**
	 * 管理平台产品列表
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/product/listSearch",method = RequestMethod.POST)
	public List<ProductDto> listSearch(@RequestBody ProductQuery query);

	/**
	 * 上标
	 * @param id
	 * @return
	 */
	@RequestMapping("/product/shelve")
	int shelve(@RequestParam("id") Long id);

	/**
	 * 下标
	 * @param id
	 * @return
	 */
	@RequestMapping("/product/unshelve")
	int unshelve(@RequestParam("id") Long id);
    
    /**
     * 推荐标
     * @param userId
     * @return
     */
    @RequestMapping("/product/getRecommend")
	public ProductDto getRecommend(@RequestParam("userId")Long userId);
    
    /**
     * 标列表，按分类划分不同区域
     * @return
     */
    @RequestMapping(value="/product/list",method = RequestMethod.GET)
	public List<ProductCategoryDto> list(@RequestParam("userId")Long userId);
    
    /**
     * 已售罄列表
     * @return
     */
    @RequestMapping(value="/product/sellOut",method = RequestMethod.POST)
	public Pagination<ProductViewDto> sellOut(@RequestBody ProductQuery query);
    
    /**
     * 产品的投资记录
     * @param productId
     */
    @RequestMapping(value="/product/investment/record",method = RequestMethod.POST)
    public Pagination<OrderDto> investmentRecord(@RequestBody OrderQuery query);
    
    /**
     * 统计投资人数
     * @param id
     * @return
     */
    @RequestMapping(value="/product/countInvestmentUser",method = RequestMethod.GET)
    public long countInvestmentUser(@RequestParam("id") Long id);
    
    /**
     * 投资
     * @param userId
     * @param productId
     * @param couponUserId
     * @param amount
     * @return
     */
    @RequestMapping(value="/product/investment",method = RequestMethod.POST)
	public ApiResponse investment(@RequestParam("userId") Long userId,@RequestParam("productId") Long productId,@RequestParam("couponUserId") Long couponUserId,@RequestParam("amount") Long amount);
	
    /**
     * 投资支付确认
     * @param userId
     * @param productId
     * @param couponUserId
     * @param amount
     * @return
     */
    @RequestMapping(value="/product/investment/pay/confirm",method = RequestMethod.GET)
    public ApiResponse investmentPayConfirm(@RequestParam("userId") Long userId,@RequestParam("productId") Long productId,@RequestParam("couponUserId") Long couponUserId,@RequestParam("amount") Long amount,@RequestParam("needBank") Boolean needBank);
    
//    /**
//     * 投资支付 ，获取验证码
//     * @param ip
//     * @param userId
//     * @param bankCodeId
//     * @param realName
//     * @param idCard
//     * @param bankCardNo
//     * @param mobile
//     * @return
//     */
//    @RequestMapping(value="/product/investment/pay/smsCode",method = RequestMethod.POST)
//	public ApiResponse investmentPaySmsCode(@RequestParam("ip")String ip,
//			@RequestParam(value = "userId",required = true)Long userId,
//			@RequestParam(value = "productId",required = true)Long productId ,
//			@RequestParam(value = "amount",required = true)Long amount, 
//			@RequestParam(value = "couponUserId",required = false) Long couponUserId ,
//			@RequestParam(value = "bankCodeId",required = false)Long bankCodeId);
    

    /**
     * 产品介绍
     * @param userId
     * @param productId
     * @param couponUserId
     * @param amount
     * @return
     */
    @RequestMapping(value="/product/introduce",method = RequestMethod.GET)
    public ProductIntroduceDto introduce(@RequestParam("id") Long id);


	/**
	 * 售罄、满标任务
	 * @return
	 */
	@RequestMapping(value="/product/sellOutTask")
	int sellOutTask();

	/**
	 * 售罄、满标
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/product/updateSellOut")
	int updateSellOut(@RequestParam("productId") Long productId);

	/**
	 * 产品到期（计息结束）任务
	 * @return
	 */
	@RequestMapping("/product/interestExpire")
	int interestExpire();

	/**
	 * 产品到期（计息结束）
	 * @return
	 */
	@RequestMapping("/product/updateInterestExpire")
	int updateInterestExpire(@RequestParam("productId") Long productId);
    
}
