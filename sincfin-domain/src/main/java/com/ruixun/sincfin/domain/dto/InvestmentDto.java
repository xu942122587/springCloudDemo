package com.ruixun.sincfin.domain.dto;

public class InvestmentDto {
	
	private boolean isPayError;
	
	private String status;
	
	private String msg;
	
	

	public InvestmentDto() {
		super();
	}

	public InvestmentDto(boolean isPayError, String status, String msg) {
		super();
		this.isPayError = isPayError;
		this.status = status;
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isPayError() {
		return isPayError;
	}

	public void setPayError(boolean isPayError) {
		this.isPayError = isPayError;
	}

}
