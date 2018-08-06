package com.ruixun.sincfin.mobile.api.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.ruixun.sincfin.common.util.RandomUtil;
import com.ruixun.sincfin.domain.dto.AccountWithdrawDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.StatisticsAccountMoneyDto;
import com.ruixun.sincfin.domain.dto.StatisticsChannelDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.enums.ProductValueDateMethodEnum;
import com.ruixun.sincfin.mobile.api.context.RequestContext;
import com.ruixun.sincfin.mobile.api.util.Excel2003Utils;
import com.ruixun.sincfin.mobile.api.util.ReportListExcel;

/**
 * Created by tiantiea on 2018/6/13.
 */
@Component
public class ExportExcelService {

	public static final String EXPORT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String EXPORT_FILE_NAME_DATETIME_FORMAT = "yyyyMMddHHmm";

	@Value("${config.fuiou}")
	private String foiouNo;
	@Value("${config.name}")
	private String platformName;

	/**
	 * 财务管理，提现管理，提现列表
	 * 
	 * @param dtoList
	 * @throws IOException
	 */
	public void exportAccountWithdraw(List<AccountWithdrawDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("提现管理"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "汇出单位账号", "用户银行卡号", "用户姓名", "", "", "银行名称", "金额(单位:元)", "备注");
					}

					@Override
					public List<Object> getRowData(int index) {
						AccountWithdrawDto dto = dtoList.get(index);
						return Lists.newArrayList(RandomUtil.random_20_sn(), foiouNo, dto.getBankCardNo(),
								dto.getUserRealName(), "", "", dto.getBankName(), moneyLong2BigDecimal(dto.getAmount()),
								platformName + "平台提现");
					}
				});
	}

	/**
	 * 账单管理，提现列表
	 * 
	 * @param dtoList
	 * @throws IOException
	 */
	public void exportStatisticsAccountWithdraw(List<StatisticsAccountMoneyDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("提现列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "用户 ID", "提现金额", "钱包余额", "申请时间", "提现成功时间");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsAccountMoneyDto dto = dtoList.get(index);
						return Lists.newArrayList(dto.getId(), dto.getUserId(), moneyLong2BigDecimal(dto.getAmount()),
								moneyLong2BigDecimal(dto.getBalance()), dateFormat(dto.getGmtWithdrawApply()),
								dateFormat(dto.getGmtCreate()));
					}
				});
	}

	public void exportStatisticsAccountRecharge(List<StatisticsAccountMoneyDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("充值列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "用户 ID", "充值编码", "充值余额", "钱包余额", "充值成功时间");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsAccountMoneyDto dto = dtoList.get(index);
						return Lists.newArrayList(dto.getId(), dto.getUserId(), dto.getObjectId(),
								moneyLong2BigDecimal(dto.getAmount()), moneyLong2BigDecimal(dto.getBalance()),
								dateFormat(dto.getGmtCreate()));
					}
				});
	}

	public void exportStatisticsInvestment(List<StatisticsAccountMoneyDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("投资列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "用户 ID", "产品名称", "投资余额", "待收总金额", "待收利息金额", "本期计息天数", "购买时间");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsAccountMoneyDto dto = dtoList.get(index);
						return Lists.newArrayList(dto.getId(), dto.getUserId(), dto.getProductTitle(),
								moneyLong2BigDecimal(dto.getAmount()),
								moneyLong2BigDecimal(dto.getAmount() + dto.getInterest()),
								moneyLong2BigDecimal(dto.getInterest()), dto.getTimeLimit(),
								dateFormat(dto.getGmtCreate()));
					}
				});
	}

	public void exportStatisticsRepayment(List<StatisticsAccountMoneyDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("还款列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "用户 ID", "项目编号", "还款总金额", "还款本金", "还款利息", "还款时间");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsAccountMoneyDto dto = dtoList.get(index);
						return Lists.newArrayList(dto.getId(), dto.getUserId(), dto.getProductId(),
								moneyLong2BigDecimal(dto.getAmount()), moneyLong2BigDecimal(dto.getPrincipal()),
								moneyLong2BigDecimal(dto.getInterest()), dateFormat(dto.getGmtCreate()));
					}
				});
	}

	public void exportStatisticsCoupon(List<StatisticsAccountMoneyDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("优惠券列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "用户 ID", "项目 ID", "优惠券金额", "使用时间", "投资金额", "投资期限");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsAccountMoneyDto dto = dtoList.get(index);
						return Lists.newArrayList(dto.getId(), dto.getUserId(), dto.getProductId(),
								moneyLong2BigDecimal(dto.getAmount()), dateFormat(dto.getGmtCreate()),
								moneyLong2BigDecimal(dto.getInvestmentAmount()), dto.getTimeLimit());
					}
				});
	}

	public void exportStatisticsCashBack(List<StatisticsAccountMoneyDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("返现列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("序号", "用户 ID", "返现金额", "返现时间");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsAccountMoneyDto dto = dtoList.get(index);
						return Lists.newArrayList(dto.getId(), dto.getUserId(), moneyLong2BigDecimal(dto.getAmount()),
								dateFormat(dto.getGmtCreate()));
					}
				});
	}

	public void exportStatisticsSellOut(List<ProductDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("募集满列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("产品编号", "产品名称", "产品类型", "产品标签", "总收益率", "贴息利率", "产品期限", "起息方式",
								"上线时间", "起息时间", "到期时间", "募集总额");
					}

					@Override
					public List<Object> getRowData(int index) {
						ProductDto dto = dtoList.get(index);

						return Lists.newArrayList(dto.getId(), dto.getTitle(), dto.getProductTypeName(),
								dto.getProductLabelName(), percentFormat(dto.getTotalInterestRate()),
								percentFormat(dto.getSubsidyInterestRate()), dto.getTimeLimit(),
								ProductValueDateMethodEnum.getTextFromCode(dto.getValueDateMethod()),
								dateFormat(dto.getGmtRelease()), dateFormat(dto.getGmtValueDate()),
								dateFormat(dto.getGmtExpirationDate()), moneyLong2BigDecimal(dto.getSellOutAmount()));
					}
				});
	}

	public void exportStatisticsChannelUser(List<UserDto> dtoList, String fileName) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName(fileName), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("用户编号", "姓名", "手机号", "注册时间");
					}

					@Override
					public List<Object> getRowData(int index) {
						UserDto dto = dtoList.get(index);

						return Lists.newArrayList(dto.getId(), dto.getRealName(), dto.getMobile(),
								dateFormat(dto.getGmtCreate()));
					}
				});
	}

	public void exportStatisticsChannel(List<StatisticsChannelDto> dtoList) throws IOException {

		Excel2003Utils.writeExcel(RequestContext.getResponse(), getFileName("渠道统计列表"), dtoList.size(),
				new ReportListExcel() {
					@Override
					public List<String> getColumnNames() {
						return Lists.newArrayList("渠道编号", "渠道名称", "激活数", "注册数", "首投数", "复投数", "首投金额（元）", "新增复投金额（元）",
								"总投资额（元）", "期间存量（元）", "当前存量（元）");
					}

					@Override
					public List<Object> getRowData(int index) {
						StatisticsChannelDto dto = dtoList.get(index);

						return Lists.newArrayList(dto.getChannelId(), dto.getChannelName(), dto.getActivateCount(),
								dto.getRegisterCount(), dto.getFirstInvestmentCount(),
								dto.getRepeatedlyInvestmentCount(),
								moneyLong2BigDecimal(dto.getFirstInvestmentAmount()),
								moneyLong2BigDecimal(dto.getRepeatedlyInvestmentAmount()),
								moneyLong2BigDecimal(dto.getTotalInvestmentAmount()),
								moneyLong2BigDecimal(dto.getTimeIntervalStock()),
								moneyLong2BigDecimal(dto.getCurrentStock()));
					}
				});
	}

	private String getFileName(String fileName) {
		return fileName + DateFormatUtils.format(new Date(), EXPORT_FILE_NAME_DATETIME_FORMAT);
	}

	private BigDecimal moneyLong2BigDecimal(Long amount) {
		if (amount == null)
			return null;
		return new BigDecimal(amount).divide(new BigDecimal(100));
	}

	private String dateFormat(Date date) {
		if (date == null)
			return null;
		return DateFormatUtils.format(date, EXPORT_DATETIME_FORMAT);
	}

	private String percentFormat(BigDecimal data) {
		if (data == null)
			return "/";
		return data.multiply(new BigDecimal(100)) + "%";
	}

}
