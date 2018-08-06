package com.ruixun.sincfin.common.exception;

/**
 * Created by t on 16/11/8.
 */
public class ParamException extends RuntimeException {
    protected Integer exceptionCode;
    /**
     *
     */
    private static final long serialVersionUID = -2357521295745486102L;

    public ParamException() {
        super();
    }

    public ParamException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ParamException(String msg, Throwable throwable, Integer code) {
        super(msg, throwable);
        this.exceptionCode = code;
    }

    public ParamException(int code, String msg) {
        super(msg, null);
        this.exceptionCode = code;
    }

    public ParamException(String msg) {
        super(msg);
    }

    public ParamException(Throwable arg0) {
        super(arg0);
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
