package com.ruixun.sincfin.common.exception;

/**
 * Created by t on 16/11/8.
 */
public class SystemException extends RuntimeException {
    protected String exceptionCode;
    /**
     *
     */
    private static final long serialVersionUID = -2357521295745486102L;

    public SystemException() {
        super();
    }

    public SystemException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
    public SystemException(String msg, Throwable throwable, String code) {
        super(msg, throwable);
        this.exceptionCode = code;
    }

    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(Throwable arg0) {
        super(arg0);
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
