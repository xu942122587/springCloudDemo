package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.List;

/**
 * @author t
 * @date 2018/5/22 20:36
 */
public class ManagerRoleQuery extends BaseRequest {

    private List<Long> idList;

    private String status;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
