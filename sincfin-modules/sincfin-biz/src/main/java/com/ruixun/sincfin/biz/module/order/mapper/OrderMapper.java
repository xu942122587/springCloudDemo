package com.ruixun.sincfin.biz.module.order.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.entity.OrderEntity;
import com.ruixun.sincfin.domain.query.OrderQuery;

public interface OrderMapper {

	int insert(OrderEntity record);

	int insertSelective(OrderEntity record);

	OrderEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(OrderEntity record);

	int updateByPrimaryKey(OrderEntity record);

	OrderEntity getByUserId(Long userId);

	OrderEntity getByOrderNum(String orderNum);

	List<OrderDto> getOrderByProduct(OrderQuery query);

	List<OrderDto> getOrderByUser(OrderQuery query);

	OrderDto getOrderById(Long id);

	long countInvestmentUser(Long productId);

	/**
	 * 管理平台投资订单接口
	 * 
	 * @param query
	 * @return
	 */
	List<OrderDto> listManagerByCondition(OrderQuery query);

	/**
	 * 订单还款
	 * 
	 * @param productId
	 * @return
	 */
	int updateRepayment(@Param("productId") Long productId);

	/**
	 * 获取支付完成的产品最后一单
	 * 
	 * @param productId
	 * @return
	 */
	OrderDto getLastOrderByProductId(@Param("productId") Long productId);

	/**
	 * 获取用户首投
	 * 
	 * @param userId
	 * @return
	 */
	OrderDto getFirstOrderByUserId(@Param("userId") Long userId);

	/**
	 * 获取用户当前标累计投资
	 * 
	 * @param userId
	 * @return
	 */
	BigDecimal getTotalInvestByUserIdAndPid(Map<String, Object> map);

	BigDecimal getTotalInvestByUserId(Map<String, Object> map);

	  
	    /**  
	     * @Description 当日新增投资用户数 
	     * @Author      HJJ
	     * @Date        2018年7月17日 下午4:08:05  
	     * @param @param date
	     * @param @return 参数  
	     * @return int 返回类型   
	     * @throws  
	     */  
	    
	Integer getInvestUserCountsByDate(@Param("date") String date);
	
	  
	    /**  
	     * @Description  当日新手标投资投资总额 
	     * @Author      HJJ  
	     * @Date        2018年7月17日 下午4:39:00  
	     * @param @param date
	     * @param @return 参数  
	     * @return int 返回类型   
	     * @throws  
	     */  
	    
	Long getInvestUserTotalNewUserAreaByDate(@Param("date") String date);
	
	  
	    /**  
	     * @Description 当日复投总额  
	     * @Author      HJJ  
	     * @Date        2018年7月17日 下午4:54:27  
	     * @param @param date
	     * @param @return 参数  
	     * @return BigDecimal 返回类型   
	     * @throws  
	     */  
	    
	Long getReInvestUserTotalNewUserAreaByDate(@Param("date") String date);
	
	  
	    /**  
	     * @Description 当日发标金额  
	     * @Author      HJJ  
	     * @Date        2018年7月17日 下午5:36:43  
	     * @param @param date
	     * @param @return 参数  
	     * @return long 返回类型   
	     * @throws  
	     */  
	    
	Long getReleaseAmountByDate(@Param("date") String date);
}