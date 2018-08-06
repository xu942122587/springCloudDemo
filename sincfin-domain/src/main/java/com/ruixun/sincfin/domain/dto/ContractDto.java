package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by tiantiea on 2018/5/24.
 */
public class ContractDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private Long contractTemplateId;

    private Long financingUserId;

    private String repaymentType;

    private BigDecimal interestRate;

    private Long amount;

    private Long usableBalance;

    private Long balance;

    private Integer timeLimit;

    private Integer productCount;

    private Date gmtStart;

    private Date gmtEnd;

    private Integer securityLevel;

    private String serviceAgreement;

    private List<FileObjectDto> serviceAgreementFileList;

    private String projectIntro;

    private String loanUse;

    private String guaranteeCompany;

    private String legalBasis;

    private FinancingUserDto financingUser;

    private String contractTemplateName;

    private String financingUserRealName;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getContractTemplateId() {
        return contractTemplateId;
    }

    public void setContractTemplateId(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public Long getFinancingUserId() {
        return financingUserId;
    }

    public void setFinancingUserId(Long financingUserId) {
        this.financingUserId = financingUserId;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(Long usableBalance) {
        this.usableBalance = usableBalance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(Integer securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getServiceAgreement() {
        return serviceAgreement;
    }

    public void setServiceAgreement(String serviceAgreement) {
        this.serviceAgreement = serviceAgreement;
    }

    public List<FileObjectDto> getServiceAgreementFileList() {
        return serviceAgreementFileList;
    }

    public void setServiceAgreementFileList(List<FileObjectDto> serviceAgreementFileList) {
        this.serviceAgreementFileList = serviceAgreementFileList;
    }

    public String getProjectIntro() {
        return projectIntro;
    }

    public void setProjectIntro(String projectIntro) {
        this.projectIntro = projectIntro;
    }

    public String getLoanUse() {
        return loanUse;
    }

    public void setLoanUse(String loanUse) {
        this.loanUse = loanUse;
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

    public FinancingUserDto getFinancingUser() {
        return financingUser;
    }

    public void setFinancingUser(FinancingUserDto financingUser) {
        this.financingUser = financingUser;
    }

    public String getContractTemplateName() {
        return contractTemplateName;
    }

    public void setContractTemplateName(String contractTemplateName) {
        this.contractTemplateName = contractTemplateName;
    }

    public String getFinancingUserRealName() {
        return financingUserRealName;
    }

    public void setFinancingUserRealName(String financingUserRealName) {
        this.financingUserRealName = financingUserRealName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
