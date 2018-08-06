package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;
import java.util.List;

public class ProductQuery extends BaseRequest {
	private static final long serialVersionUID = -6057255384903698691L;

	private Long productId;

	private Long contractId;

	private String status;

	private String title;

	private Date gmtStartRelease;

	private Date gmtEndRelease;

	private Date gmtStartValueDate;

	private Date gmtEndValueDate;

	private Date gmtStartExpirationDate;

	private Date gmtEndExpirationDate;

	private Long unsoldAmount;

	private Date gmtStartSellOut;

	private Date gmtEndSellOut;
	
	

	public Date getGmtStartSellOut() {
		return gmtStartSellOut;
	}

	public void setGmtStartSellOut(Date gmtStartSellOut) {
		this.gmtStartSellOut = gmtStartSellOut;
	}

	public Date getGmtEndSellOut() {
		return gmtEndSellOut;
	}

	public void setGmtEndSellOut(Date gmtEndSellOut) {
		this.gmtEndSellOut = gmtEndSellOut;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getGmtStartRelease() {
		return gmtStartRelease;
	}

	public void setGmtStartRelease(Date gmtStartRelease) {
		this.gmtStartRelease = gmtStartRelease;
	}

	public Date getGmtEndRelease() {
		return gmtEndRelease;
	}

	public void setGmtEndRelease(Date gmtEndRelease) {
		this.gmtEndRelease = gmtEndRelease;
	}

	public Date getGmtStartValueDate() {
		return gmtStartValueDate;
	}

	public void setGmtStartValueDate(Date gmtStartValueDate) {
		this.gmtStartValueDate = gmtStartValueDate;
	}

	public Date getGmtEndValueDate() {
		return gmtEndValueDate;
	}

	public void setGmtEndValueDate(Date gmtEndValueDate) {
		this.gmtEndValueDate = gmtEndValueDate;
	}

	public Date getGmtStartExpirationDate() {
		return gmtStartExpirationDate;
	}

	public void setGmtStartExpirationDate(Date gmtStartExpirationDate) {
		this.gmtStartExpirationDate = gmtStartExpirationDate;
	}

	public Date getGmtEndExpirationDate() {
		return gmtEndExpirationDate;
	}

	public void setGmtEndExpirationDate(Date gmtEndExpirationDate) {
		this.gmtEndExpirationDate = gmtEndExpirationDate;
	}

	public Long getUnsoldAmount() {
		return unsoldAmount;
	}

	public void setUnsoldAmount(Long unsoldAmount) {
		this.unsoldAmount = unsoldAmount;
	}
}
