package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.UserBankChangeDto;
import com.ruixun.sincfin.domain.query.UserBankChangeQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiea on 2018/6/4.
 */
@FeignClient("${biz.feign.name}")
public interface UserBankChangeClient {

    @RequestMapping("/userBankChange/listPageByCondition")
    Pagination<UserBankChangeDto> listPageByCondition(@RequestBody UserBankChangeQuery query);

    @RequestMapping("/userBankChange/getById")
    UserBankChangeDto getById(@RequestParam("id") Long id);

    @RequestMapping("/userBankChange/updateAuditPass")
    int updateAuditPass(@RequestParam("id") Long id,
                  @RequestParam("auditorId") Long auditorId);

    @RequestMapping("/userBankChange/updateAuditReject")
    int updateAuditReject(@RequestParam("id") Long id,
                    @RequestParam("auditorId") Long auditorId,
                    @RequestParam("remark") String remark);
    
    
    @RequestMapping(value="/userBankChange/changeCardApply",method = RequestMethod.POST)
	public void changeCardApply(@RequestParam("bankCodeId")Long bankCodeId, @RequestParam("disBankCodeId")Long disBankCodeId, 
			@RequestParam("idCardFace")String idCardFace, @RequestParam("idCardBack")String idCardBack);


}
