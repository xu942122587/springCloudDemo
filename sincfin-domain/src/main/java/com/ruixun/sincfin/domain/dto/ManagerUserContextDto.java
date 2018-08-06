package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by t on 2017/4/14.
 */
public class ManagerUserContextDto implements Serializable {

    private String token;
    private ManagerUserDto managerUser;

    private List<String> permissionUrlList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ManagerUserDto getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(ManagerUserDto managerUser) {
        this.managerUser = managerUser;
    }

    public List<String> getPermissionUrlList() {
        return permissionUrlList;
    }

    public void setPermissionUrlList(List<String> permissionUrlList) {
        this.permissionUrlList = permissionUrlList;
    }
}
