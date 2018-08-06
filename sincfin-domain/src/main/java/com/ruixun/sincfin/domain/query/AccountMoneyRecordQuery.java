package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

public class AccountMoneyRecordQuery  extends BaseRequest {
	
	
	private static final long serialVersionUID = 1L;
	//资金类型：withdraw：提现 profit：收益 cash_back：返现 investment ：投资 recharge ：充值
	private String type;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
