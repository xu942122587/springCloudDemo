package com.ruixun.sincfin.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;

/**
 * Created by t on 16/11/8.
 */
public class BizException extends RuntimeException {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Integer exceptionCode;
	/**
	 *
	 */
	private static final long serialVersionUID = -2357521295745486102L;

	public BizException() {
		super();
	}

	public BizException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public BizException(String msg, Throwable throwable, Integer code) {
		super(msg, throwable);
		this.exceptionCode = code;
	}

	public BizException(ApiStatusEnum statusEnum) {
		super(statusEnum.getMsg(), null);
		logger.error(statusEnum.getMsg());
		this.exceptionCode = statusEnum.getStatus();
	}

	public BizException(Integer code, String msg) {
		super(msg, null);
		this.exceptionCode = code;
	}

	public BizException(String msg) {
		super(msg);
	}

	public BizException(Throwable arg0) {
		super(arg0);
	}

	public Integer getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(Integer exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
