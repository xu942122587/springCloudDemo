package com.ruixun.sincfin.biz.config;

import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by t on 2017/4/17.
 */
@ControllerAdvice
public class BizExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(BizExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse bizExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
//        ApiResponse apiResponse = new ApiResponse();
//        Integer exceptionCode = ((BizException) ex).getExceptionCode();
//        if (exceptionCode == null) {
//            exceptionCode = ApiStatusEnum.COMMON_BIZ_SERVICE_ERROR.getStatus();
//        }
//        apiResponse.setStatus(exceptionCode);
//        apiResponse.setMsg(ex.getMessage());
        BizException bizException = (BizException) ex;
        return new ApiResponse(bizException.getExceptionCode(), bizException.getMessage());
    }
}
