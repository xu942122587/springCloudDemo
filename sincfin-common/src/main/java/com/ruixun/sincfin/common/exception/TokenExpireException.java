package com.ruixun.sincfin.common.exception;

/**
 * Created by t on 2017/4/17.
 */
public class TokenExpireException extends RuntimeException{

    public TokenExpireException() {
        super();
    }

    public TokenExpireException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public TokenExpireException(String msg) {
        super(msg);
    }

    public TokenExpireException(Throwable arg0) {
        super(arg0);
    }

}
