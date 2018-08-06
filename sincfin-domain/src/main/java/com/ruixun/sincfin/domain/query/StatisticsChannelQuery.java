package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;
import java.util.List;

public class StatisticsChannelQuery extends BaseRequest {
	
	private static final long serialVersionUID = 1L;

	private List<Long> channelIdList;

	private String channelIds;

	private Long channelId;

	private String exportType;

	private String channelName;

	private Date gmtStart;
	private Date gmtEnd;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<Long> getChannelIdList() {
		return channelIdList;
	}

	public void setChannelIdList(List<Long> channelIdList) {
		this.channelIdList = channelIdList;
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

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
}

