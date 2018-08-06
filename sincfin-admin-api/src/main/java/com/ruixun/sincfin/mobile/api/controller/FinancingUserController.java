package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.FinancingUserClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.FinancingUserDto;
import com.ruixun.sincfin.domain.enums.FinancingUserStatusEnum;
import com.ruixun.sincfin.domain.enums.FinancingUserTypeEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.FinancingUserQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author t
 * @date 2018/5/22 15:15
 */
@RestController
@RequestMapping("financingUser")
public class FinancingUserController {

    @Resource
    private FinancingUserClient financingUserClient;

    @RequestMapping("listPageEnterprise")
    public ApiResponse listPageEnterprise(FinancingUserQuery query) {

        query.setType(FinancingUserTypeEnum.ENTERPRISE.getCode());

        return ApiResponse.successApiResponse(financingUserClient.listPageByCondition(query));

    }

    @RequestMapping("listPagePersonal")
    public ApiResponse listPagePersonal(FinancingUserQuery query) {

        query.setType(FinancingUserTypeEnum.PERSONAL.getCode());

        return ApiResponse.successApiResponse(financingUserClient.listPageByCondition(query));

    }

    @RequestMapping("list")
    public ApiResponse list(FinancingUserQuery query) {
        query.setStatus(FinancingUserStatusEnum.NORMAL.getCode());
        return ApiResponse.successApiResponse(financingUserClient.listByCondition(query));

    }

    @RequestMapping("getById")
    public ApiResponse getById(Long id) {
        return ApiResponse.successApiResponse(financingUserClient.getById(id));

    }

    @RequestMapping("insertEnterprise")
    public ApiResponse insertEnterprise(FinancingUserDto financingUserDto) {

        Assert.assertNotNull(financingUserDto);
        Assert.assertNotNull(financingUserDto.getRealName());
        Assert.assertNotNull(financingUserDto.getBusinessLicenseNo());
        Assert.assertNotNull(financingUserDto.getMobile());

        financingUserDto.setType(FinancingUserTypeEnum.ENTERPRISE.getCode());
        return ApiResponse.successApiResponse(financingUserClient.insert(financingUserDto));

    }

    @RequestMapping("updateEnterprise")
    public ApiResponse updateEnterprise(FinancingUserDto financingUserDto) {

        Assert.assertNotNull(financingUserDto);
        Assert.assertNotNull(financingUserDto.getRealName());
        Assert.assertNotNull(financingUserDto.getBusinessLicenseNo());
        Assert.assertNotNull(financingUserDto.getMobile());
        financingUserDto.setType(FinancingUserTypeEnum.ENTERPRISE.getCode());

        return ApiResponse.successApiResponse(financingUserClient.update(financingUserDto));

    }

    @RequestMapping("insertPersonal")
    public ApiResponse insertPersonal(FinancingUserDto financingUserDto) {

        Assert.assertNotNull(financingUserDto);
        Assert.assertNotNull(financingUserDto.getRealName());
        Assert.assertNotNull(financingUserDto.getIdCardNo());
        Assert.assertNotNull(financingUserDto.getMobile());
        financingUserDto.setType(FinancingUserTypeEnum.PERSONAL.getCode());

        return ApiResponse.successApiResponse(financingUserClient.insert(financingUserDto));

    }


}
