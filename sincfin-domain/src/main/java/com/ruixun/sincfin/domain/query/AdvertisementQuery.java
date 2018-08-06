package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiantiea on 2018/6/5.
 */
public class AdvertisementQuery extends BaseRequest {

    private String position;

    private String title;
    private String status;

    private Date gmtStartStart;
    private Date gmtEndStart;

    private Date gmtStartEnd;
    private Date gmtEndEnd;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtStartStart() {
        return gmtStartStart;
    }

    public void setGmtStartStart(Date gmtStartStart) {
        this.gmtStartStart = gmtStartStart;
    }

    public Date getGmtEndStart() {
        return gmtEndStart;
    }

    public void setGmtEndStart(Date gmtEndStart) {
        this.gmtEndStart = gmtEndStart;
    }

    public Date getGmtStartEnd() {
        return gmtStartEnd;
    }

    public void setGmtStartEnd(Date gmtStartEnd) {
        this.gmtStartEnd = gmtStartEnd;
    }

    public Date getGmtEndEnd() {
        return gmtEndEnd;
    }

    public void setGmtEndEnd(Date gmtEndEnd) {
        this.gmtEndEnd = gmtEndEnd;
    }
}
