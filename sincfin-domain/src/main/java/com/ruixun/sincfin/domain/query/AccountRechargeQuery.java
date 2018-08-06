package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiantiea on 2018/5/24.
 */
public class AccountRechargeQuery extends BaseRequest {

    private Long userId;

    private Date gmtStartCreate;
    private Date gmtEndCreate;

    private String type;

    private String status;
    
    /**
     * 需要剔除掉的状态
     */
    private String excludeStatus;

    private String userRealName;

    private String userMobile;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

	public String getExcludeStatus() {
		return excludeStatus;
	}

	public void setExcludeStatus(String excludeStatus) {
		this.excludeStatus = excludeStatus;
	}
}
