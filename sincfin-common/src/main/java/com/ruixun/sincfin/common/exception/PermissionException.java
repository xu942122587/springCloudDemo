package com.ruixun.sincfin.common.exception;

/**
 * Created by t on 2017/4/17.
 */
public class PermissionException extends RuntimeException{

    public PermissionException() {
        super();
    }

    public PermissionException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public PermissionException(String msg) {
        super(msg);
    }

    public PermissionException(Throwable arg0) {
        super(arg0);
    }

}
