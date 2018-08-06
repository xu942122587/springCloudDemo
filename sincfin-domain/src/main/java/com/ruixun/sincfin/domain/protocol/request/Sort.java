package com.ruixun.sincfin.domain.protocol.request;


import java.io.Serializable;

/**
 * Created by tiantiea on 15/6/9.
 */
public class Sort implements Serializable {

    private String direction;
    private String property;

    public Sort(String property, String direction) {
        this.property = property;
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }
}
