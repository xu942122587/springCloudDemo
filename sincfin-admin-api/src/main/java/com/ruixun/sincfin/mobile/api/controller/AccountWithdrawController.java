package com.ruixun.sincfin.mobile.api.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.AccountWithdrawClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountWithdrawDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.AccountWithdrawQuery;
import com.ruixun.sincfin.mobile.api.service.ExportExcelService;

/**
 * Created by tiantiea on 2018/5/28.
 */
@RestController
@RequestMapping("accountWithdraw")
public class AccountWithdrawController extends BaseController {

	@Resource
	private AccountWithdrawClient accountWithdrawClient;
	@Resource
	private ExportExcelService exportExcelService;

	@RequestMapping("listManagerPageByUser")
	public ApiResponse listManagerPageByUser(AccountWithdrawQuery query) {
		Assert.assertNotNull(query.getUserId());
		return ApiResponse.successApiResponse(accountWithdrawClient.listManagerPage(query));

	}

	@RequestMapping("listManagerPage")
	public ApiResponse listManagerPage(AccountWithdrawQuery query) {

		return ApiResponse.successApiResponse(accountWithdrawClient.listManagerPage(query));

	}

	@RequestMapping("exportExcel")
    public void exportExcel(AccountWithdrawQuery query) throws IOException {
		if (StringUtils.isNotEmpty(query.getIds())) {
            query.setIdList(Arrays.asList(query.getIds().split(",")).stream()
                    .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()));
        }
        query.setPageSize(query.getIdList()==null?0:query.getIdList().size());
        Pagination<AccountWithdrawDto> pagination = accountWithdrawClient.listManagerPage(query);

        exportExcelService.exportAccountWithdraw(pagination.getData());
    }

	@RequestMapping("auditPass")
	public ApiResponse auditPass(String ids) {
		Assert.assertNotNull(ids);
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim()))
				.collect(Collectors.toList());

		return ApiResponse.successApiResponse(accountWithdrawClient.updateAuditPass(idList, getContextManagerUserId()));
	}

	@RequestMapping("auditReject")
	public ApiResponse auditReject(String ids) {
		Assert.assertNotNull(ids);
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim()))
				.collect(Collectors.toList());

		return ApiResponse
				.successApiResponse(accountWithdrawClient.updateAuditReject(idList, getContextManagerUserId()));
	}

	@RequestMapping("auditSuccess")
	public ApiResponse auditSuccess(String ids) {
		Assert.assertNotNull(ids);
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim()))
				.collect(Collectors.toList());

		return ApiResponse
				.successApiResponse(accountWithdrawClient.updateAuditSuccess(idList, getContextManagerUserId()));
	}

}
