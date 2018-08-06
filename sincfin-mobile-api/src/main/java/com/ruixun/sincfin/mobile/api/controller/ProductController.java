
package com.ruixun.sincfin.mobile.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.AccountClient;
import com.ruixun.sincfin.biz.feign.client.CouponUserClient;
import com.ruixun.sincfin.biz.feign.client.ProductClient;
import com.ruixun.sincfin.biz.feign.client.ProductLabelClient;
import com.ruixun.sincfin.biz.feign.client.UserClient;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.common.util.IpUtils;
import com.ruixun.sincfin.common.util.ObjectUtils;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.dto.CouponUserDto;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.dto.PayConfirmDto;
import com.ruixun.sincfin.domain.dto.ProductCategoryDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.ProductIntroduceDto;
import com.ruixun.sincfin.domain.dto.ProductViewDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.enums.AccountRechargeTypeEnum;
import com.ruixun.sincfin.domain.enums.CouponUserStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductTypeEnum;
import com.ruixun.sincfin.domain.enums.ProductValueDateMethodEnum;
import com.ruixun.sincfin.domain.enums.UserRealNameAuthEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.CouponUserQuery;
import com.ruixun.sincfin.domain.query.OrderQuery;
import com.ruixun.sincfin.domain.query.ProductQuery;
import com.ruixun.sincfin.mobile.api.annotation.Auth;
import com.ruixun.sincfin.mobile.api.config.SincfinConfiguration;
import com.ruixun.sincfin.mobile.api.vo.CouponUserVo;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController{

	@Resource
    private UserClient userClient;
	@Resource
	private ProductClient productClient;
	@Resource
	private ProductLabelClient productLabelClient;
	@Resource
	private AccountClient accountClient;
	@Resource
    private CouponUserClient couponUserClient;
	@Resource
	private SincfinConfiguration config;
	
	
	/**
     * 首页推荐
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value="/recommend",method = RequestMethod.GET)
    public ApiResponse recommend() {
	    Long userId = getCurrentUserId();
    	ProductDto productDto= productClient.getRecommend(userId);
    	ProductViewDto productVo = new ProductViewDto();
    	BeanHelper.copyProperties(productVo, productDto);
    	
        return ApiResponse.successApiResponse(productVo);
    }

	/**
     * 产品列表
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ApiResponse list(ProductQuery query) {
	    Long userId = getCurrentUserId();
    	List<ProductCategoryDto> projectCategorys = productClient.list(userId);
        return ApiResponse.successApiResponse(projectCategorys);
    }

    /**
     * 售罄列表
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value="/sellOut",method = RequestMethod.GET)
    public ApiResponse sellOut(ProductQuery query) {

    	Pagination<ProductViewDto> productDtos = productClient.sellOut(query);

        return ApiResponse.successApiResponse(productDtos);

    }


    /**
     * 标的详情
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value="/details",method = RequestMethod.GET)
    public ApiResponse details(@RequestParam(value="productId",required=true) Long productId) {
    	ProductDto productDto=productClient.getById(productId);
    	if(productDto==null){
    		throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST.getStatus(), ApiStatusEnum.PRODUCE_NOT_EXIST.getMsg());
    	}
    	Map<String,Object> param = new HashMap<String,Object>();
    	
    	ProductViewDto productVo = new ProductViewDto();
    	
    	BeanHelper.copyProperties(productVo, productDto);
    	ProductValueDateMethodEnum valueDateMethodEnum = ProductValueDateMethodEnum.fromCode(productVo.getValueDateMethod());
    	if(valueDateMethodEnum!=null){
    		productVo.setValueDateMethod(valueDateMethodEnum.getText());
    	}
    	int investmentUsers = (int)productClient.countInvestmentUser(productVo.getId());
    	
    	param.put("product", productVo);
    	param.put("investmentUsers", investmentUsers);
    	
        return ApiResponse.successApiResponse(param);
    }
    
    
    /**
     * 项目详情
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value="/introduce",method = RequestMethod.GET)
    public ApiResponse introduce(@RequestParam(value="productId",required=true) Long productId) {
    	
    	ProductDto productDto=productClient.getById(productId);
    	if(productDto==null){
    		throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST.getStatus(), ApiStatusEnum.PRODUCE_NOT_EXIST.getMsg());
    	}
    	ProductIntroduceDto productIntroduce = productClient.introduce(productId);
    	
        return ApiResponse.successApiResponse(productIntroduce);
    }
    
    
    
    
    
    
    /**
     * 投资记录
     * @param mobile
     * @param password
     * @return
     */
    @Auth
    @RequestMapping(value="/investment/record",method = RequestMethod.GET)
    public ApiResponse investmentRecord(OrderQuery query) {
    	if(query.getProductId()==null || query.getProductId()==0 ){
    		return new ApiResponse(ApiStatusEnum.CLIENT_PARAM_ERROR);
    	}
    	Pagination<OrderDto> orderDtos = productClient.investmentRecord(query);
        return ApiResponse.successApiResponse(orderDtos);
    }




    /**
     * 投资确认
     * @param mobile
     * @param password
     * @return
     */
    @Auth
    @RequestMapping(value="/investment/confirm",method = RequestMethod.GET)
    public ApiResponse investmentConfirm(@RequestParam(value="productId",required=true) Long productId) {
	    Long userId = getCurrentUserId();
	    UserDto userDto = userClient.getById(userId);
    	//用户是否进行了风险评测
    	if(userDto.getRiskType()==null){
    		ApiResponse response = new ApiResponse(ApiStatusEnum.USER_NOT_ASSERT_RISK);
    		response.setData(config.getRiskTestUrl("investmentConfirm"));
    		response.setMsg(config.getRiskTestUrl("investmentConfirm"));
    		return response;
    	}
    	ProductDto productDto=productClient.getById(productId);
    	assertCanInvestment(userDto,productDto);
    	
    	AccountDto accountDto = accountClient.getByUserId(userDto.getId());
    	Boolean canUseCoupon = couponUserClient.canUseCoupon(userDto.getId(),productDto.getId());
    	
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("product", productDto);
    	param.put("walletBalance", accountDto.getWalletBalance());
    	param.put("canUseCoupon", canUseCoupon);
    	param.put("timestamp", System.currentTimeMillis());
        return ApiResponse.successApiResponse(param);
    }
    
    
    private void assertCanInvestment(UserDto userDto, ProductDto productDto) {
    	if(productDto==null){
    		throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST);
    	}
    	if(productDto.getGmtRelease().compareTo(new Date())>0){
    		throw new BizException(ApiStatusEnum.PRODUCE_STATUS_NOT_ON_SALE);
    	}
    	boolean isNewUser = userClient.isNewUser(userDto.getId());
    	if(!isNewUser){
    		if(productDto.getProductTypeCode().equals(ProductTypeEnum.NEW_USER.getCode())){
    			throw new BizException(ApiStatusEnum.PRODUCE_NEW_USER_LIMIT);
    		}
    	}
	}

	/**
     * 使用优惠券
     * @param mobile
     * @param password
     * @return
     */
    @Auth
    @RequestMapping(value="/useCoupon",method = RequestMethod.GET)
    public ApiResponse useCoupon(@RequestParam(value="productId",required=true) Long productId,
    		@RequestParam(value="amount",defaultValue = "0") Long amount,CouponUserQuery query) {
	    Long userId = getCurrentUserId();
    	ProductDto productDto=productClient.getById(productId);
    	if(productDto==null){
    		throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST.getStatus(), ApiStatusEnum.PRODUCE_NOT_EXIST.getMsg());
    	}
    	query.setUserId(userId);
    	query.setStatus(CouponUserStatusEnum.UNUSED.getCode());
    	Pagination<CouponUserDto> pageCoupons = couponUserClient.listByUserId(query);
    	List<CouponUserDto> listCopons = pageCoupons.getData();
    	List<CouponUserVo> listCoponVos = new ArrayList<CouponUserVo>();
    	for(CouponUserDto dto : listCopons){
    		if(!dto.getStatus().equals(CouponUserStatusEnum.UNUSED.getCode())){
    			continue;
    		}
    		CouponUserVo vo = new CouponUserVo();
    		BeanHelper.copyProperties(vo, dto);
    		String productTypeCode = productDto.getProductTypeCode();
    		if(!dto.getProductTypeList().contains(productTypeCode)){
    			vo.setEnable(false);
    			vo.setDescription("该红包不适用于此标的");
    		}else if(amount>0 && amount<dto.getLimitPrice()){
    			vo.setEnable(false);
    			vo.setDescription("投资金额不符");
    		}else if( productDto.getTimeLimit() < dto.getLimitDays()){
    			vo.setEnable(false);
    			vo.setDescription("产品期限不符");
    		}else{
    			vo.setEnable(true);
    		}
    		vo.setProductTypeList(CouponUserVo.parseType(dto.getProductTypeList()));
    		listCoponVos.add(vo);
    	}
    	
        return ApiResponse.successApiResponse(new Pagination(query, listCoponVos, pageCoupons.getTotalCount()));
    }
    
    /**
     * 投资 支付确认
     * @param mobile
     * @param password
     * @return
     */
    @Auth
    @RequestMapping(value="/investment/pay/confirm",method = RequestMethod.GET)
    public ApiResponse investmentPayConfirm(@RequestParam(value="productId",required=true) Long productId,
    		@RequestParam(value="amount",required=true) Long amount,
    		@RequestParam(value="couponUserId",defaultValue = "0") Long couponUserId) {
	    Long userId = getCurrentUserId();
    	ApiResponse response = productClient.investmentPayConfirm(userId,productId,couponUserId,amount,true);
        return response;
    }
    
    
    
    /**
     * 投资 支付确认 获取验证码
     * @param mobile
     * @param password
     * @return
     * @throws Exception 
     */
    @Auth
    @RequestMapping(value="/investment/pay/smsCode",method = RequestMethod.POST)
    public ApiResponse investmentPaySmsCode(@RequestParam(value="productId",required=true) Long productId,
    		@RequestParam(value="amount",required=true) Long amount,
    		@RequestParam(value="couponUserId",defaultValue = "0") Long couponUserId,
    		Long bankCodeId,String realName, String idCardNo,String bankCardNo,String mobile) throws Exception {
    	assertInvestmentPaySmsCodeParam(bankCodeId,realName,idCardNo,bankCardNo,mobile);
    	Long userId = getCurrentUserId();
    	UserDto userDto = userClient.getById(userId);
    	boolean realNameAuth = userDto.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
        if (realNameAuth) {
            realName = userDto.getRealName();
            idCardNo = userDto.getIdCardNo();
        }
    	PayConfirmDto payConfirmDto = null;
    	/**
    	 * 获取确认结果
    	 */
    	ApiResponse payConfirmResponse = productClient.investmentPayConfirm(userId,productId,couponUserId,amount,false);
    	if(!payConfirmResponse.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS) ){
    		return payConfirmResponse;
    	}else{
    		payConfirmDto = (PayConfirmDto) ObjectUtils.mapToObject((Map)payConfirmResponse.getData(), PayConfirmDto.class);
    	}
    	if(payConfirmDto.getBankCodePayAmount()<=0){
    		throw new BizException(ApiStatusEnum.INVESTMENT_CHANNEL_NOT_SUPPORT);
    	}
	    
    	/**
    	 * 如果是新卡投资，先去绑卡
    	 */
	    if(bankCodeId ==null || bankCodeId <=0){
        	ApiResponse bindBankResponse= userClient.bindBankCode(userId, realName, idCardNo, bankCardNo, mobile,false);
        	if(!bindBankResponse.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS) ){
	    		return bindBankResponse;
	    	}else{
	    		bankCodeId = Long.valueOf(bindBankResponse.getData().toString());
	    	}
        }
	    /**
	     * 充值 获取验证码
	     */
	    String ip = IpUtils.getRemoteHost(getRequest());
	    ApiResponse rechargeSmsCodeResponse = accountClient.rechargeSmsCode(ip , userId, bankCodeId, payConfirmDto.getBankCodePayAmount(),AccountRechargeTypeEnum.INVESTMENT.getCode());
	    if(!rechargeSmsCodeResponse.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS) ){
	    	return rechargeSmsCodeResponse;
	    }else{
	    	String rechargeNum = (String)rechargeSmsCodeResponse.getData();
	    	payConfirmDto.setRechargeNum(rechargeNum);
	    }
	    
        return ApiResponse.successApiResponse(payConfirmDto);
    }
    
    
    /**
     * 断言参数正确
     * @param bankCodeId
     * @param realName
     * @param idCard
     * @param bankCardNo
     * @param mobile
     */
    private void assertInvestmentPaySmsCodeParam(Long bankCodeId,String realName, String idCard,String bankCardNo,String mobile){
    	
    	if(bankCodeId!=null && bankCodeId>0){
    		if(!StringUtils.isAllBlank(realName,idCard,bankCardNo,mobile)){
    			throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
    		}
    	}else{
    		if(StringUtils.isAllBlank(bankCardNo,mobile)){
    			throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
    		}
    	}
    }

    /**
     * 投资
     * @param mobile
     * @param password
     * @return
     */
    @Auth
    @RequestMapping(value="/investment",method = RequestMethod.POST)
    public ApiResponse investment(@RequestParam(value="productId",required=true) Long productId,
    		@RequestParam(value="amount",required=true) Long amount,
    		@RequestParam(value="couponUserId",defaultValue = "0") Long couponUserId,
    		String rechargeNum,String smsCode) {
    	if(amount<=0){
        	return new ApiResponse(ApiStatusEnum.CLIENT_PARAM_ERROR);
        }
	    Long userId = getCurrentUserId();
	    if(StringUtils.isNotEmpty(rechargeNum) ){
	    	String ip = IpUtils.getRemoteHost(getRequest());
	    	if(StringUtils.isEmpty(smsCode)){
	    		return new ApiResponse(ApiStatusEnum.CLIENT_PARAM_ERROR);
	    	}
	    	ApiResponse rechargeResponse = accountClient.recharge(ip, rechargeNum, smsCode);
	    	if(!rechargeResponse.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS) ){
	    		return rechargeResponse;
	    	}
	    }
    	ApiResponse response = productClient.investment(userId,productId,couponUserId,amount);
        return response;
    }
}

