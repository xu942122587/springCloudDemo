package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ProductLabelClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.ProductLabelDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ProductLabelQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>项目标签管理 开放接口类</p>
 *
 * @author qj
 * @version 1.0
 * @date 2018-05-23
 */
@RestController
@RequestMapping("/productLabel")
public class ProductLabelController extends BaseController {

    @Resource
    private ProductLabelClient productLabelClient;

    @RequestMapping("/list")
    public ApiResponse list(ProductLabelQuery query) {

        return ApiResponse.successApiResponse(productLabelClient.listByCondition(query));

    }

    @RequestMapping("/listPage")
    public ApiResponse listPage(ProductLabelQuery query) {

        return ApiResponse.successApiResponse(productLabelClient.listPageByCondition(query));

    }

    @RequestMapping("/insert")
    public ApiResponse insert(ProductLabelDto productLabelDto) {

        Assert.assertNotNull(productLabelDto);
        Assert.assertNotNull(productLabelDto.getName());

        return ApiResponse.successApiResponse(productLabelClient.insert(productLabelDto));

    }

    @RequestMapping("/update")
    public ApiResponse update(ProductLabelDto productLabelDto) {

        Assert.assertNotNull(productLabelDto);
        Assert.assertNotNull(productLabelDto.getId());
        Assert.assertNotNull(productLabelDto.getName());

        return ApiResponse.successApiResponse(productLabelClient.update(productLabelDto));

    }

    @RequestMapping("delete")
    public ApiResponse delete(@RequestParam("id") Long id) {
        Assert.assertNotNull(id);
        return ApiResponse.successApiResponse(productLabelClient.delete(id));
    }


}
