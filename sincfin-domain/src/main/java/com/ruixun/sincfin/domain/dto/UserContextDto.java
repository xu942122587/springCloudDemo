package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;

/**
 * Created by t on 2017/4/14.
 */
public class UserContextDto implements Serializable {

    private String token;
    private UserDto user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
