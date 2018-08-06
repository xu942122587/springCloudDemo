package com.ruixun.sincfin.common.springmvc;

import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.exception.ParamException;
import com.ruixun.sincfin.common.exception.PermissionException;
import com.ruixun.sincfin.common.exception.TokenExpireException;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by t on 2017/4/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ParamException.class)
    @ResponseBody
    public ApiResponse paramExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        ApiResponse apiResponse = new ApiResponse(ApiStatusEnum.COMMON_PARAM_ERROR, ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ApiResponse bizExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        Integer exceptionCode = ((BizException) ex).getExceptionCode();
        if (exceptionCode == null) {
            exceptionCode = ApiStatusEnum.COMMON_BIZ_SERVICE_ERROR.getStatus();
        }
        apiResponse.setStatus(exceptionCode);
        apiResponse.setMsg(ex.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(value = TokenExpireException.class)
    @ResponseBody
    public ApiResponse tokenExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        ApiResponse apiResponse = new ApiResponse(ApiStatusEnum.COMMON_NOT_LOGIN);
        return apiResponse;
    }

    @ExceptionHandler(value = PermissionException.class)
    @ResponseBody
    public ApiResponse permissionExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        ApiResponse apiResponse = new ApiResponse(ApiStatusEnum.COMMON_NO_PERMISSION);
        return apiResponse;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(HttpServletRequest req, Exception ex) throws Exception {

        logger.error("server error", ex);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(ApiStatusEnum.COMMON_SERVICE_ERROR.getStatus());
        apiResponse.setMsg(ex.getMessage());
//        apiResponse.setMsg("系统异常!");
        return apiResponse;
    }

}
