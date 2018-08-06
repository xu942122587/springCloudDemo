package com.ruixun.sincfin.domain.dto;

/**
 * 融资用户融资信息统计
 * @author rx
 *
 */
public class FinancingUserCountDto {
	
	/**
	 * 累计借款
	 */
	private Long countFinancing;
	
	/**
	 * 已还款
	 */
	private Long countRepayment;

	public Long getCountFinancing() {
		return countFinancing;
	}

	public void setCountFinancing(Long countFinancing) {
		this.countFinancing = countFinancing;
	}

	public Long getCountRepayment() {
		return countRepayment;
	}

	public void setCountRepayment(Long countRepayment) {
		this.countRepayment = countRepayment;
	}
	
	
	

}
