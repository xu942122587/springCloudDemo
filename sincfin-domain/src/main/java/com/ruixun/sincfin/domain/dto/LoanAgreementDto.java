package com.ruixun.sincfin.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 借款协议
 * @author rx
 *
 */
public class LoanAgreementDto {
	
	/**
	 * 协议编号
	 */
	private String agreementNum;
	/**
	 * （甲方）出借人
	 */
	
	private String borrower;
	/**
	 * （甲方）身份证号码/营业执照号
	 */
	private String borrowerCardNo;
	/**
	 * (甲方)在诚意金融平台用户名为
	 */
	private String borrowerPhone;
	/**
	 * (乙方)借款人
	 */
	private String lender;
	/**
	 * (乙方)身份证号码/营业执照号
	 */
	private String lenderCardNo;
	
	/**
	 * 借款金额
	 */
	private Long money;
	/**
	 * 利率
	 */
	private BigDecimal interest;
	/**
	 * 借款期限
	 */
	private Integer timeLimit;

	public String getAgreementNum() {
		return agreementNum;
	}

	public void setAgreementNum(String agreementNum) {
		this.agreementNum = agreementNum;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getBorrowerCardNo() {
		return borrowerCardNo;
	}

	public void setBorrowerCardNo(String borrowerCardNo) {
		this.borrowerCardNo = borrowerCardNo;
	}

	public String getBorrowerPhone() {
		return borrowerPhone;
	}

	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}

	public String getLender() {
		return lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}

	public String getLenderCardNo() {
		return lenderCardNo;
	}

	public void setLenderCardNo(String lenderCardNo) {
		this.lenderCardNo = lenderCardNo;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	
}
