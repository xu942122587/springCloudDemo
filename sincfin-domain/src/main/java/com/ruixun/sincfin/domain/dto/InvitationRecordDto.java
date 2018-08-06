package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;



public class InvitationRecordDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String inviter ;
	
	private boolean invested;

	public String getInviter() {
		return mobileEncryption(inviter);
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public boolean isInvested() {
		return invested;
	}

	public void setInvested(boolean invested) {
		this.invested = invested;
	}
	
	
	/**
	 * 隐藏手机号码中间四位
	 * @param mobile
	 * @return
	 */
	public String mobileEncryption(String mobile){
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	}

}
