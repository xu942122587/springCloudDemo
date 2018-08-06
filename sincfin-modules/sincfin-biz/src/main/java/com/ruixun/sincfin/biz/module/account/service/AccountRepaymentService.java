package com.ruixun.sincfin.biz.module.account.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ruixun.sincfin.biz.feign.client.AccountRepaymentClient;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMoneyRecordMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountRepaymentMapper;
import com.ruixun.sincfin.biz.module.order.mapper.OrderMapper;
import com.ruixun.sincfin.biz.module.user.service.UserBankChangeService;
import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.client.message.PushMessageClient;
import com.ruixun.sincfin.common.MessageContent;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountRepaymentDto;
import com.ruixun.sincfin.domain.dto.RepaymentMSNDto;
import com.ruixun.sincfin.domain.entity.*;
import com.ruixun.sincfin.domain.enums.AccountMoneyRecordTypeEnum;
import com.ruixun.sincfin.domain.enums.AccountRepaymentStatusEnum;
import com.ruixun.sincfin.domain.enums.AccountRepaymentTypeEnum;
import com.ruixun.sincfin.domain.enums.OrderStatusEnum;
import com.ruixun.sincfin.domain.query.AccountRepaymentQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by tiantiea on 2018/5/31.
 */
@RestController
@RequestMapping("accountRepayment")
public class AccountRepaymentService implements AccountRepaymentClient {

	private Logger logger = LoggerFactory.getLogger(AccountRepaymentService.class);

	@Resource
	private AccountRepaymentMapper accountRepaymentMapper;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private AccountMoneyRecordMapper accountMoneyRecordMapper;
	@Resource
	private OrderMapper orderMapper;

	@Autowired
	private PushMessageClient messageClient;

	@RequestMapping("listPageByCondition")
	public Pagination<AccountRepaymentDto> listPageByCondition(@RequestBody AccountRepaymentQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<AccountRepaymentDto> dtoList = accountRepaymentMapper.listByCondition(query);
		PageInfo pageInfo = new PageInfo(dtoList);

		return new Pagination(query, dtoList, (int) pageInfo.getTotal());
	}

	/**
	 * 账户还款
	 * 
	 * @param productId
	 * @return
	 */
	public Long updateRepayment(Long productId) {

		AccountRepaymentQuery accountRepaymentQuery = new AccountRepaymentQuery();
		accountRepaymentQuery.setProductId(productId);
		List<AccountRepaymentDto> accountRepaymentDtoList = accountRepaymentMapper
				.listByCondition(accountRepaymentQuery);
		Long total = 0L;

		// todo 批量
		for (AccountRepaymentDto dto : accountRepaymentDtoList) {
			if (!AccountRepaymentStatusEnum.NO_REPAYMENT.getCode().equals(dto.getStatus())) {
				continue;
			}
			String orderNum = dto.getOrderNum();
			OrderEntity orderEntity = orderMapper.getByOrderNum(orderNum);
			if (!OrderStatusEnum.PAYMENT_SUCCESS.getCode().equals(orderEntity.getStatus())) {
				continue;
			}

			if (AccountRepaymentTypeEnum.PRINCIPAL_INTEREST.getCode().equals(dto.getType())) {
				Long userId = dto.getUserId();
				AccountEntity accountEntity = accountMapper.getByUserId(userId);

				Long amount = dto.getAmount();
				Long principal = dto.getPrincipal();
				Long interest = dto.getInterest();
				total += amount;
				accountMapper.updateRepayment(userId, amount, principal, interest);

				AccountRepaymentEntity accountRepaymentEntity = new AccountRepaymentEntity();
				accountRepaymentEntity.setId(dto.getId());
				accountRepaymentEntity.setStatus(AccountRepaymentStatusEnum.REPAYMENT.getCode());
				accountRepaymentEntity.setGmtRepayment(new Date());
				accountRepaymentMapper.updateByPrimaryKeySelective(accountRepaymentEntity);

				AccountMoneyRecordEntity accountMoneyRecordEntity = new AccountMoneyRecordEntity();
				accountMoneyRecordEntity.setUserId(userId);
				accountMoneyRecordEntity.setAccountId(dto.getAccountId());
				accountMoneyRecordEntity.setType(AccountMoneyRecordTypeEnum.PRINCIPAL_INTEREST.getCode());
				accountMoneyRecordEntity.setObjectId(orderNum);
				accountMoneyRecordEntity.setTitle(AccountMoneyRecordTypeEnum.PRINCIPAL_INTEREST.getText());
				accountMoneyRecordEntity.setAmount(amount);
				// todo
				accountMoneyRecordEntity.setBalance(accountEntity.getWalletBalance() + amount);
				accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);

				OrderEntity updateOrderEntity = new OrderEntity();
				updateOrderEntity.setId(orderEntity.getId());
				updateOrderEntity.setStatus(OrderStatusEnum.REPAYMENT.getCode());
				orderMapper.updateByPrimaryKeySelective(updateOrderEntity);
			}

		}
		return total;
	}

	@Override
	@RequestMapping("repaymentMSNTask")
	public void repaymentMSNTask() {

		AccountRepaymentQuery query = new AccountRepaymentQuery();
		query.setStatus(AccountRepaymentStatusEnum.NO_REPAYMENT.getCode());
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DATE, 1);
		query.setGmtStartRepayment(calendar.getTime());

		calendar.add(Calendar.DATE, 1);
		query.setGmtEndRepayment(calendar.getTime());
		List<AccountRepaymentDto> repaymentDtoList = accountRepaymentMapper.listByCondition(query);
		if (CollectionUtils.isEmpty(repaymentDtoList)) {
			return;
		}
		Map<String, RepaymentMSNDto> repaymentMSNDtoMap = Maps.newHashMap();

		for (AccountRepaymentDto repaymentDto : repaymentDtoList) {
			String userMobile = repaymentDto.getUserMobile();
			RepaymentMSNDto repaymentMSNDto = repaymentMSNDtoMap.get(userMobile);
			if (repaymentMSNDto == null) {
				repaymentMSNDto = new RepaymentMSNDto();
				repaymentMSNDtoMap.put(userMobile, repaymentMSNDto);
			}
			repaymentMSNDto.setCapitalCount(repaymentMSNDto.getCapitalCount() + 1);
			repaymentMSNDto.setCapitalAmount(repaymentMSNDto.getCapitalAmount() + repaymentDto.getPrincipal());
			repaymentMSNDto.setInterestCount(repaymentMSNDto.getInterestCount() + 1);
			repaymentMSNDto.setInterestAmount(repaymentMSNDto.getInterestAmount() + repaymentDto.getInterest());
		}

		repaymentMSNDtoMap.forEach((k, v) -> {
			try {
				String content = MessageContent.getAccountRepaymentContent(v);
				PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(), Arrays.asList(k), content);
				messageClient.send(message);
			} catch (Exception e) {
				logger.error("account repayment msn send fail", e);
			}
		});

	}
}
