package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.List;

/**
 * Created by tiea on 2018/6/3.
 */
public class BankQuery extends BaseRequest {

    private String name;

    private List<String> codes;

    private String Status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
