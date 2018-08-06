package com.ruixun.sincfin.mobile.api.constant;

import java.util.Date;

import com.ruixun.sincfin.common.util.AmountUtils;
import com.ruixun.sincfin.common.util.DateUtils;
import com.ruixun.sincfin.common.util.StringUtils;

/**
 * 消息类容 常量（先放到实体类中，后面有时间规整到消息配置文件中）
 * @author rx
 *
 */

public class MessageContent {
	
	private final static String SMSCODE_CONTENT = "尊敬的用户，您好。您的手机验证码是{}，若非您本人操作，请忽略该信息。";
	
	private final static String RECHARGE_SUCCESS_CONTENT = "尊敬的用户，您已成功充值{}元，请您及时投资。感谢您一如既往对的支持和关注。";
	
	private final static String WITHDRAW_APPLY_CONTENT = "尊敬的用户，您好！您于{}发起一笔{}元的提现，提现金额具体到账时间以实际银行处理时间为准，请您注意查收，如非本人操作请联系客服。";
	
	private final static String CHANGE_CARD_APPLY_CONTENT = "尊敬的用户，您已提交了银行卡换卡申请，我们工作人员将会在近期联系您进行进一步审核。若非您本人操作，请在我们工作人员联系您时说明，避免银行卡被替换。";
	
	private final static String UNTIE_BANK_CARD_SUCCESS_CONTENT = "尊敬的用户，您的银行卡已经成功解绑。详情请登录APP查看。";
	
	public static String getSmsCodeContent(String smsCode){
		return StringUtils.format(SMSCODE_CONTENT, smsCode);
	}
	
	public static String getRechargeSuccessContent(long amount) throws Exception{
		return StringUtils.format(RECHARGE_SUCCESS_CONTENT, AmountUtils.changeF2Y(amount));
	}
	
	public static String getWithdrawApplyContent(Date time,long amount) throws Exception{
		return StringUtils.format(WITHDRAW_APPLY_CONTENT, DateUtils.formatDate(time) ,AmountUtils.changeF2Y(amount));
	}
	
	public static String getChangeCardApplyContent(){
		return CHANGE_CARD_APPLY_CONTENT;
	}
	
	public static String getUntieBankCardSuccessContent(){
		return UNTIE_BANK_CARD_SUCCESS_CONTENT;
	}
	
	

}
