package com.ruixun.sincfin.mobile.api.config;

import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.JacksonUtil;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tiea on 2018/6/7.
 */
@Component
public class BizErrorDecoder implements ErrorDecoder {


    private ErrorDecoder delegate = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String body = "";
        try {
            body = Util.toString(response.body().asReader());
        } catch (IOException e) {
            ;
        }
        ApiResponse apiResponse = JacksonUtil.readValue(body, ApiResponse.class);
        if (apiResponse != null && apiResponse.getStatus() != null
                && !apiResponse.getStatus().equals(ApiStatusEnum.COMMON_SUCCESS.getStatus())) {
            return new BizException(apiResponse.getStatus(), apiResponse.getMsg());
        }
        return delegate.decode(methodKey, response);
    }
}
