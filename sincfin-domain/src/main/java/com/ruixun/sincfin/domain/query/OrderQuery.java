package com.ruixun.sincfin.domain.query;

import java.util.Date;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

public class OrderQuery extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private Long productId;

	private Long userId;

	private String userRealName;

	private String userMobile;

	private String userAccountType;
	/* 满标时间 */
	private String gmtSellOut;

	private String productTitle;

	private Date gmtStartCreate;

	private Date gmtEndCreate;

	private Date gmtStartExpirationDate;

	private Date gmtEndExpirationDate;
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

	public String getGmtSellOut() {
		return gmtSellOut;
	}

	public void setGmtSellOut(String gmtSellOut) {
		this.gmtSellOut = gmtSellOut;
	}

	// financing:投资中 interestIn:计息中 repayment:已完成 failure:投资失败
	private String status;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAccountType() {
		return userAccountType;
	}

	public void setUserAccountType(String userAccountType) {
		this.userAccountType = userAccountType;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Date getGmtStartCreate() {
		return gmtStartCreate;
	}

	public void setGmtStartCreate(Date gmtStartCreate) {
		this.gmtStartCreate = gmtStartCreate;
	}

	public Date getGmtEndCreate() {
		return gmtEndCreate;
	}

	public void setGmtEndCreate(Date gmtEndCreate) {
		this.gmtEndCreate = gmtEndCreate;
	}

	public Date getGmtStartExpirationDate() {
		return gmtStartExpirationDate;
	}

	public void setGmtStartExpirationDate(Date gmtStartExpirationDate) {
		this.gmtStartExpirationDate = gmtStartExpirationDate;
	}

	public Date getGmtEndExpirationDate() {
		return gmtEndExpirationDate;
	}

	public void setGmtEndExpirationDate(Date gmtEndExpirationDate) {
		this.gmtEndExpirationDate = gmtEndExpirationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
