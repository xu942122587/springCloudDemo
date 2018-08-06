package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

/**
 * @author t
 * @date 2018/5/23 14:54
 */
public class UserBankQuery extends BaseRequest {

    private Long userId;
    
    private Long replaceBankCodeId;

    private String bindStatus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

	public Long getReplaceBankCodeId() {
		return replaceBankCodeId;
	}

	public void setReplaceBankCodeId(Long replaceBankCodeId) {
		this.replaceBankCodeId = replaceBankCodeId;
	}
}
