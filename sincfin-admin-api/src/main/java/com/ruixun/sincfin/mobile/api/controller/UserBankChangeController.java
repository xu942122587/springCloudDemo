package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.UserBankChangeClient;
import com.ruixun.sincfin.biz.feign.client.UserBankClient;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.UserBankChangeQuery;
import com.ruixun.sincfin.domain.query.UserBankQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author t
 * @date 2018/5/23 15:02
 */
@RestController
@RequestMapping("/userBankChange")
public class UserBankChangeController extends BaseController {

    @Resource
    private UserBankChangeClient userBankChangeClient;

    @RequestMapping("listPage")
    public ApiResponse listPage(UserBankChangeQuery query) {
        return ApiResponse.successApiResponse(userBankChangeClient.listPageByCondition(query));

    }

    @RequestMapping("getById")
    public ApiResponse getById(Long id) {
        return ApiResponse.successApiResponse(userBankChangeClient.getById(id));
    }

    @RequestMapping("auditPass")
    public ApiResponse auditPass(Long id) {
        return ApiResponse.successApiResponse(userBankChangeClient.updateAuditPass(id, getContextManagerUserId()));
    }

    @RequestMapping("auditReject")
    public ApiResponse auditReject(Long id, String remark) {
        return ApiResponse.successApiResponse(userBankChangeClient.updateAuditReject(id, getContextManagerUserId(), remark));
    }

}
