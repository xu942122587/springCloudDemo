package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ruixun.sincfin.domain.entity.OrderEntity;
import com.ruixun.sincfin.domain.entity.OrderProductEntity;
import com.ruixun.sincfin.domain.enums.OrderStatusEnum;

public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 订单id
	 */
	private Long id;

	/**
	 * 订单编号
	 */
	private String orderNum;
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 账户 id
	 */
	private Long accountId;
	/**
	 * 用户的手机号
	 */
	private String userMobile;

	/**
	 * 用户真实姓名
	 */
	private String userRealName;

	/**
	 * 投资金额
	 */
	private Long amount;
	/**
	 * 支付金额
	 */
	private Long paymentAmount;

	/**
	 * 利息
	 */
	private Long interest;

	/**
	 * 产品id
	 */
	private Long productId;
	/**
	 * 产品标题
	 */
	private String productTitle;

	/**
	 * 总利率
	 */
	private BigDecimal totalInterestRate;
	/**
	 * 贴息利率
	 */
	private BigDecimal subsidyInterestRate;
	/**
	 * 起息时间
	 */
	private Date gmtValueDate;
	/**
	 * 到期时间
	 */
	private Date gmtExpirationDate;
	/** 满标时间 */
	private Date gmtSellOut;

	/**
	 * 优惠券id
	 */
	private Long couponUserId;
	/**
	 * 优惠券类别
	 */
	private String couponType;
	/**
	 * 优惠券金额
	 */
	private Long bonusAmount;
	/**
	 * 状态
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 更新时间
	 */
	private Date gmtModified;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 产品期限
	 */
	private Integer timeLimit;

	/**
	 * 还款方式
	 */
	private String valueDateMethod;
	/**
	 * 设备类型
	 */
	private String deviceType;
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道Id
	 */
	private Integer channelId;
	/**
	 * 邀请人ID
	 */
	private Integer inviterId;
	/**
	 * 邀请人手机号
	 */
	private String inviterMobile;

	public OrderDto() {
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getInviterId() {
		return inviterId;
	}

	public void setInviterId(Integer inviterId) {
		this.inviterId = inviterId;
	}

	public String getInviterMobile() {
		return inviterMobile;
	}

	public void setInviterMobile(String inviterMobile) {
		this.inviterMobile = inviterMobile;
	}

	public Date getGmtSellOut() {
		return gmtSellOut;
	}

	public void setGmtSellOut(Date gmtSellOut) {
		this.gmtSellOut = gmtSellOut;
	}

	public OrderDto(OrderEntity order, OrderProductEntity orderProductEntity, CouponUserDto couponUserDto) {
		this.id = order.getId();
		this.orderNum = order.getOrderNum();
		this.userId = order.getUserId();
		// this.mobile = null;
		this.productId = orderProductEntity.getProductId();
		// this.productTitle = null;
		this.totalInterestRate = orderProductEntity.getTotalInterestRate();
		this.subsidyInterestRate = orderProductEntity.getSubsidyInterestRate();
		this.gmtValueDate = orderProductEntity.getGmtValueDate();
		this.gmtExpirationDate = orderProductEntity.getGmtExpirationDate();
		this.amount = order.getAmount();
		this.paymentAmount = order.getPaymentAmount();
		this.interest = order.getInterest();
		this.status = order.getStatus();
		this.gmtCreate = order.getGmtCreate();

		if (couponUserDto != null) {
			// 优惠券
			this.couponUserId = couponUserDto.getId();
			this.couponType = couponUserDto.getBonusType();
			this.bonusAmount = couponUserDto.getAmount();
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public BigDecimal getTotalInterestRate() {
		return totalInterestRate;
	}

	public void setTotalInterestRate(BigDecimal totalInterestRate) {
		this.totalInterestRate = totalInterestRate;
	}

	public BigDecimal getSubsidyInterestRate() {
		return subsidyInterestRate;
	}

	public void setSubsidyInterestRate(BigDecimal subsidyInterestRate) {
		this.subsidyInterestRate = subsidyInterestRate;
	}

	public Date getGmtValueDate() {
		return gmtValueDate;
	}

	public void setGmtValueDate(Date gmtValueDate) {
		this.gmtValueDate = gmtValueDate;
	}

	public Date getGmtExpirationDate() {
		return gmtExpirationDate;
	}

	public void setGmtExpirationDate(Date gmtExpirationDate) {
		this.gmtExpirationDate = gmtExpirationDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Long getInterest() {
		return interest;
	}

	public void setInterest(Long interest) {
		this.interest = interest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Long getCouponUserId() {
		return couponUserId;
	}

	public void setCouponUserId(Long couponUserId) {
		this.couponUserId = couponUserId;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public Long getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Long bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getValueDateMethod() {
		return valueDateMethod;
	}

	public void setValueDateMethod(String valueDateMethod) {
		this.valueDateMethod = valueDateMethod;
	}

	public Integer getExpirationDay() {
		if (this.getGmtExpirationDate() == null || new Date().compareTo(this.getGmtExpirationDate()) > 0) {
			return 0;
		} else {
			return getDaysBetweenToDate(this.getGmtExpirationDate(), new Date());
		}
	}

	public Integer getInterestRateDay() {
		return this.getTimeLimit();
	}

	public String getStatusDesc() {
		OrderStatusEnum statusEnum = OrderStatusEnum.fromCode(this.getStatus());
		if (statusEnum != null) {
			return statusEnum.getText();
		}
		return null;
	}

	/**
	 * 计算两个日期相差天数
	 * 
	 * @param end
	 * @param start
	 * @return
	 */
	private int getDaysBetweenToDate(Date end, Date start) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();

		calst.setTime(start);
		caled.setTime(end);
		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
		return days;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}
