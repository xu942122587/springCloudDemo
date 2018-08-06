package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ProductClient;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.enums.ProductStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ProductQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/31.
 */

@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

    @Resource
    private ProductClient productClient;

    @RequestMapping("listSearch")
    public ApiResponse listSearch(ProductQuery query) {
        Assert.assertNotNull(query);
        query.setStatus(ProductStatusEnum.ON_SALE.getCode());
        return ApiResponse.successApiResponse(productClient.listSearch(query));
    }

    @RequestMapping("listPage")
    public ApiResponse listPage(ProductQuery query) {
        return ApiResponse.successApiResponse(productClient.listPageByCondition(query));
    }

    @RequestMapping("listPageByContract")
    public ApiResponse listPageByContract(ProductQuery query) {
        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getContractId());
        return ApiResponse.successApiResponse(productClient.listPageByCondition(query));
    }

    @RequestMapping("getDetailById")
    public ApiResponse getDetailById(Long id) {
        return ApiResponse.successApiResponse(productClient.getDetailById(id));
    }

    @RequestMapping("update")
    public ApiResponse update(ProductDto productDto) {
        Assert.assertNotNull(productDto);
        Assert.assertNotNull(productDto.getId());
        Assert.assertNotNull(productDto.getAmountMin());
        Assert.assertNotNull(productDto.getAmountMax());
        long amountMin = productDto.getAmountMin();
        long amountMax = productDto.getAmountMax();
        // 单位：分
        if (amountMin > amountMax && amountMax != 0) {
            throw new BizException(ApiStatusEnum.PRODUCE_AMOUNT_MAX_LT_AMOUNT_MIN);
        }
        if (amountMin < 10000) {
            throw new BizException(ApiStatusEnum.PRODUCE_AMOUNT_MIN_TO_LITTLE);
        }
        if (amountMin % 10000 != 0) {
            throw new BizException(ApiStatusEnum.PRODUCE_AMOUNT_MIN_MULTIPLE);
        }
        if (amountMax != 0 && amountMax % amountMin != 0) {
            throw new BizException(ApiStatusEnum.PRODUCE_AMOUNT_MAX_MULTIPLE_AMOUNT_MIN);
        }

        return ApiResponse.successApiResponse(productClient.update(productDto));
    }

    /**
     * 上标
     * @param id
     * @return
     */
    @RequestMapping("shelve")
    public ApiResponse shelve(Long id) {
        return ApiResponse.successApiResponse(productClient.shelve(id));
    }

    /**
     * 下标
     * @param id
     * @return
     */
    @RequestMapping("unshelve")
    public ApiResponse unshelve(Long id) {
        return ApiResponse.successApiResponse(productClient.unshelve(id));
    }

}
