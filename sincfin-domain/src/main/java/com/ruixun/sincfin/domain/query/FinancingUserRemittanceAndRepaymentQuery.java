package com.ruixun.sincfin.domain.query;

import java.math.BigDecimal;
import java.util.Date;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

/**
 * Created by tiantiea on 2018/6/6.
 */
public class FinancingUserRemittanceAndRepaymentQuery extends BaseRequest {
	
	/**
	* @Fields (用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = -4036999634142056896L;
	/**

	 * 融资合同名称
	 */
	private String contractTitle;
	
	/**
	 * 融资方
	 */
	private String financingUserRealName;
	/**
	 * 是否需要打款
	 */
	private String remittanceStatus;
	
	/**
	 * 汇款情况
	 */
	private String repaymentStatus;
	
	/**
	 * 期限
	 */
	private String timeLimit;
	
	/**
	 * 总利率开始
	 */
	private BigDecimal totalInterestRateStart;
	
	/**
	 * 总利率结束
	 */
	private BigDecimal totalInterestRateEnd;
	
	/**
	 * 总金额开始
	 */
	private Long productAmountStart;
	
	/**
	 * 总金额结束
	 */
	private Long productAmountEnd;
	
	/**
	 * 资产名称
	 */
	private String title;
	
	/**
	 * 结标时间开始
	 */
	private Date gmtSellOutStart;
    /**
     * 结标时间结束
     */
    private Date gmtSellOutEnd;
	
    /**
       *回款时间 开始
     */
    private Date gmtExpirationDateStart;
    /**
     * 回款时间 结束
     */
    private Date gmtExpirationDateEnd;
    
    private Date gmtStartCreate;
    private Date gmtEndCreate;
    
    public String getContractTitle() {
		return contractTitle;
	}

	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}

	public String getFinancingUserRealName() {
		return financingUserRealName;
	}

	public void setFinancingUserRealName(String financingUserRealName) {
		this.financingUserRealName = financingUserRealName;
	}

	public String getRemittanceStatus() {
		return remittanceStatus;
	}

	public void setRemittanceStatus(String remittanceStatus) {
		this.remittanceStatus = remittanceStatus;
	}

	public String getRepaymentStatus() {
		return repaymentStatus;
	}

	public void setRepaymentStatus(String repaymentStatus) {
		this.repaymentStatus = repaymentStatus;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public BigDecimal getTotalInterestRateStart() {
		return totalInterestRateStart;
	}

	public void setTotalInterestRateStart(BigDecimal totalInterestRateStart) {
		this.totalInterestRateStart = totalInterestRateStart;
	}

	public BigDecimal getTotalInterestRateEnd() {
		return totalInterestRateEnd;
	}

	public void setTotalInterestRateEnd(BigDecimal totalInterestRateEnd) {
		this.totalInterestRateEnd = totalInterestRateEnd;
	}

	public Long getProductAmountStart() {
		return productAmountStart;
	}

	public void setProductAmountStart(Long productAmountStart) {
		this.productAmountStart = productAmountStart;
	}

	public Long getProductAmountEnd() {
		return productAmountEnd;
	}

	public void setProductAmountEnd(Long productAmountEnd) {
		this.productAmountEnd = productAmountEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getGmtSellOutStart() {
		return gmtSellOutStart;
	}

	public void setGmtSellOutStart(Date gmtSellOutStart) {
		this.gmtSellOutStart = gmtSellOutStart;
	}

	public Date getGmtSellOutEnd() {
		return gmtSellOutEnd;
	}

	public void setGmtSellOutEnd(Date gmtSellOutEnd) {
		this.gmtSellOutEnd = gmtSellOutEnd;
	}

	public Date getGmtExpirationDateStart() {
		return gmtExpirationDateStart;
	}

	public void setGmtExpirationDateStart(Date gmtExpirationDateStart) {
		this.gmtExpirationDateStart = gmtExpirationDateStart;
	}

	public Date getGmtExpirationDateEnd() {
		return gmtExpirationDateEnd;
	}

	public void setGmtExpirationDateEnd(Date gmtExpirationDateEnd) {
		this.gmtExpirationDateEnd = gmtExpirationDateEnd;
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
}
