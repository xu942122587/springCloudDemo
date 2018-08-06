package com.ruixun.sincfin.domain.dto;

import java.util.List;

public class ProductIntroduceDto {
	private String uses;
	private String agreement;

	private String billBank;

	private Object billImgPath;
	/**
	 * id
	 */
	private Long id;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 期限
	 */
	private Integer timeLimit;

	/**
	 * 起息方式
	 */
	private String valueDateMethod;

	/**
	 * 金额
	 */
	private Long amount;
	/**
	 * 安全等级
	 */
	private Integer securityLevel;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 借款用途
	 */
	private String loanUse;

	/**
	 * 借款人服务协议
	 */
	private String serviceAgreement;

	/**
	 * 借款人服务协议
	 */
	private List<String> serviceAgreementUrls;

	/**
	 * 借款人营业执照图
	 */
	private String businessLicenseImage;

	/**
	 * 借款人身份证正面
	 */
	private String idCardFaceImage;

	/**
	 * 借款人身份证反面
	 */
	private String idCardBackImage;

	/**
	 * 借款人借款记录
	 */
	private FinancingUserCountDto financingUserCount;

	/**
	 * 担保公司
	 */
	private String guaranteeCompany;

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getBillBank() {
		return billBank;
	}

	public void setBillBank(String billBank) {
		this.billBank = billBank;
	}

	public Object getBillImgPath() {
		return billImgPath;
	}

	public void setBillImgPath(Object billImgPath) {
		this.billImgPath = billImgPath;
	}

	/**
	 * 法律依据
	 */
	private String legalBasis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	public String getServiceAgreement() {
		return serviceAgreement;
	}

	public void setServiceAgreement(String serviceAgreement) {
		this.serviceAgreement = serviceAgreement;
	}

	public String getBusinessLicenseImage() {
		return businessLicenseImage;
	}

	public void setBusinessLicenseImage(String businessLicenseImage) {
		this.businessLicenseImage = businessLicenseImage;
	}

	public String getIdCardFaceImage() {
		return idCardFaceImage;
	}

	public void setIdCardFaceImage(String idCardFaceImage) {
		this.idCardFaceImage = idCardFaceImage;
	}

	public String getIdCardBackImage() {
		return idCardBackImage;
	}

	public void setIdCardBackImage(String idCardBackImage) {
		this.idCardBackImage = idCardBackImage;
	}

	public FinancingUserCountDto getFinancingUserCount() {
		return financingUserCount;
	}

	public void setFinancingUserCount(FinancingUserCountDto financingUserCount) {
		this.financingUserCount = financingUserCount;
	}

	public String getGuaranteeCompany() {
		return guaranteeCompany;
	}

	public void setGuaranteeCompany(String guaranteeCompany) {
		this.guaranteeCompany = guaranteeCompany;
	}

	public String getLegalBasis() {
		return legalBasis;
	}

	public void setLegalBasis(String legalBasis) {
		this.legalBasis = legalBasis;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public List<String> getServiceAgreementUrls() {
		return serviceAgreementUrls;
	}

	public void setServiceAgreementUrls(List<String> serviceAgreementUrls) {
		this.serviceAgreementUrls = serviceAgreementUrls;
	}

}
