package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by tiea on 2018/5/25.
 */
public class AccountWithdrawQuery extends BaseRequest {

	private String ids;
	private List<Long> idList;
	private List<String> statusList;

	private Long userId;
	private Long userBankId;

	private String userRealName;
	private String userMobile;

	private Date gmtStartCreate;
	private Date gmtEndCreate;

	private Date gmtStartAudit;
	private Date gmtEndAudit;

	private String status;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
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

	public Date getGmtStartAudit() {
		return gmtStartAudit;
	}

	public void setGmtStartAudit(Date gmtStartAudit) {
		this.gmtStartAudit = gmtStartAudit;
	}

	public Date getGmtEndAudit() {
		return gmtEndAudit;
	}

	public void setGmtEndAudit(Date gmtEndAudit) {
		this.gmtEndAudit = gmtEndAudit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public Long getUserBankId() {
		return userBankId;
	}

	public void setUserBankId(Long userBankId) {
		this.userBankId = userBankId;
	}
}
