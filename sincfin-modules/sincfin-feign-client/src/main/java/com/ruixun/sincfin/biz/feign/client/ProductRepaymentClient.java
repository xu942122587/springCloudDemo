package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ProductRepaymentDto;
import com.ruixun.sincfin.domain.query.ProductRepaymentQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiea on 2018/5/31.
 */
@FeignClient("${biz.feign.name}")
public interface ProductRepaymentClient {

    @RequestMapping("/productRepayment/listPageByCondition")
    Pagination<ProductRepaymentDto> listPageByCondition(@RequestBody ProductRepaymentQuery query);

    /**
     * 产品还款
     * @param productId
     * @return
     */
    @RequestMapping("/productRepayment/updateRepayment")
    int updateRepayment(@RequestParam("productId") Long productId);

}
