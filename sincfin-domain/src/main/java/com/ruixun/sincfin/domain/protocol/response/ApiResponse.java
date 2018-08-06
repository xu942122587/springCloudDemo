package com.ruixun.sincfin.domain.protocol.response;


import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import org.apache.commons.lang3.StringUtils;

    /**
 * Created by t on 16/11/21.
 */
public class ApiResponse {

    public static final Integer DEFAULT_STATUS_SUCCESS = ApiStatusEnum.COMMON_SUCCESS.getStatus();

    private Integer status;
    private Object data;
    private String msg;

    public ApiResponse() {
        this.status = DEFAULT_STATUS_SUCCESS;
    }

    public ApiResponse(Object data) {
        this.status = DEFAULT_STATUS_SUCCESS;
        this.data = data;
        System.out.println(status+"|"+msg);
    }

    public ApiResponse(int status, String msg) {
    	System.out.println(status+"|"+msg);
        this.status = status;
        this.msg = msg;
    }

    public ApiResponse(ApiStatusEnum statusEnum) {
        this.status = statusEnum.getStatus();
        this.msg = statusEnum.getMsg();
    	System.out.println(status+"|"+msg);

    }

    public ApiResponse(ApiStatusEnum statusEnum, String msg) {
        this.status = statusEnum.getStatus();
        if (StringUtils.isEmpty(msg)) {
            msg = statusEnum.getMsg();
        }
        this.msg = msg;
    	System.out.println(status+"|"+msg);
    }

    public ApiResponse(int status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    	System.out.println(status+"|"+msg);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ApiResponse paramErrorApiResponse() {
        return  new ApiResponse(ApiStatusEnum.COMMON_PARAM_ERROR);
    }

    public static ApiResponse paramErrorApiResponse(String msg) {
        return  new ApiResponse(ApiStatusEnum.COMMON_PARAM_ERROR.getStatus(), msg);
    }

    public static ApiResponse successApiResponse(Object data) {
        return  new ApiResponse(data);
    }

    public static ApiResponse successApiResponse() {
        return  new ApiResponse();
    }

    public ApiResponse withData(Object data) {
        this.data = data;
        return this;
    }
}
