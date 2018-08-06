package com.ruixun.sincfin.domain.protocol;

/**
 * @author t
 * @date 2018/5/22 10:00
 */
public enum ApiStatusEnum {


    COMMON_SUCCESS (200, "操作成功！"),
    CLIENT_PARAM_ERROR(400, "客户端参数错误！"),
    COMMON_NOT_LOGIN (401, "用户未登陆！"),
    COMMON_NO_PERMISSION (403, "用户没有权限！"),
    COMMON_PARAM_ERROR (501, "参数错误！"),
    COMMON_BIZ_SERVICE_ERROR (502, "业务异常！"),
    COMMON_SERVICE_ERROR (503, "服务器异常！"),
//    COMMON_TOKEN_EXPIRE (601, "TOKEN 失效！"),
    

    DATA_NOT_FOUND (5000001, "数据资源未发现"),
    // 管理员 5020001 - 5020099
    // 用户   5020100 - 5020199

    MANAGER_USER_NAME_NOT_EXIST (5020001, "管理员用户不存在！"),
    MANAGER_PASSWORD_ERROR (5020002, "密码错误！"),
    MANAGER_USER_ID_NOT_EXIST (5020003, "管理员用户不存在！"),
    MANAGER_USER_STATUS_NOT_ENABLED (5020004, "该管理员用户已停用！"),
    MANAGER_USER_ROLE_STATUS_NOT_ENABLED (5020005, "该管理员用户角色已停用！"),
    MANAGER_ROLE_EXIST_USER_NOT_DELETE (5020006, "角色挂靠有用户，无法删除！"),

    //用户
    USER_MOBILE_NOT_EXIST (5020101, "用户不存在！"),
    USER_PASSWORD_ERROR (5020102, "密码错误！"),
    USER_MOBILE_ALREADY_EXIST (5020103, "手机号码已被注册"),
    USER_MOBILE_FORMAT_ERROR (5020105, "手机号码格式错误"),
    USER_SMSCODE_ERROR (5020106, "短信验证码错误"),
    USER_ID_CARD_EXIST (5020107, "此身份证号码已实名其他账号"),
    USER_BANK_CARD_EXIST (5020108, "此银行卡已绑定"),
    USER_BIND_BANK_FAIL (5020109, "绑卡失败"),
    USER_BANK_CARD_NOT_EXIST  (5020110, "未绑卡或银行卡不存在"),
    USER_PAY_CHANNEL_ERROR  (5020111, "此通道不支持该卡支付"),
    USER_RECHARGE_FAIL (5020112, "充值失败"),
    USER_RECHARGE_RECORD_NOT_EXIST (5020113, "充值记录不存在"),
    USER_RECHARGE_REPEAT_SUBMIT (5020114, "充值不可重复提交"),
    USER_RECHARGE_RESULT_UNKNOWN (5020115, "充值结果未知，请稍后查询"),
    USER_WITHDRAW_TIME_OVERRUN (5020116, "提现机会已用光"),
    USER_WITHDRAW_AMOUNT_OVERRUN (5020117, "账户余额不足或银行卡提现额度不足"),
    INVITER_USER_ID_NOT_EXIST (5020118, "邀请用户不存在！"),
    INVITER_MOBILE_FORMAT_ERROR (5020119, "邀请用户手机格式错误！"),
    USER_MOBILE_ERROR (5020120, "手机号码错误"),
    USER_ID_NOT_EXIST (5020121, "用户不存在！"),
    USER_BANK_CHANGE_NOT_EXIST (5020122, "银行卡变更记录不存在！"),
    USER_BANK_CHANGE_STATUS_NOT_PENDING (5020123, "只有未审核记录才能审核，请刷新页面！"),
    USER_NOT_ASSERT_RISK (5020125, "请进行风险评测"),
    USER_FROZEN (5020126, "用户被冻结"),

    USER_BANK_STATUS_EXCEPTION (5020127, "银行卡状态异常！"),
    
    //用户账号
    INSUFFICIENT_ACCOUNT_BALANCE (5020201, "账号余额不足"),
    NOT_BOUND_BRANK_CARD (5020202, "未绑定银行卡"),
    WITHDRAW_BANKCARD_FORBID_UNBIND(5020203, "提现中的银行卡不支持绑定银操作"),


    ACCOUNT_WITHDRAW_STATUS_ERROR (5020203, "账户提现审核状态错误！"),

    // 合同
    CONTRACT_ID_NOT_EXIST (5020501, "合同不存在！"),
    CONTRACT_USABLE_BALANCE_NOT_SUFFICIENT (5020502, "可绑余额不足！"),
    CONTRACT_STATUS_CANNOT_GENERATE_PRODUCT (5020503, "合同信息没完善，无法生成产品！"),
    
    //产品
    PRODUCE_NOT_EXIST (5030101, "标的不存在！"),
    PRODUCE_NOT_SUFFICIENT_FUNDS (5030102, "投资金额已超过剩余可投金额"),
    PRODUCE_NOT_EDITABLE (5030103, "产品不可编辑！"),
    PRODUCE_NOT_SHELVE (5030104, "产品不可上标！"),
    PRODUCE_NOT_UNSHELVE (5030105, "产品不可下标！"),
    PRODUCE_SELL_NOT_UNSHELVE (5030106, "产品已被购买过，不可下标！"),
    PRODUCE_STATUS_NOT_ON_SALE (5030107, "产品未在销售中，无法购买！"),
    PRODUCE_MIN_AMOUNT_LIMIT (5030108, "投资金额不能小于最小投资金额"),
    PRODUCE_MIN_AMOUNT_MULTIPLE_LIMIT (5030109, "投资金额必须是最小金额的整数倍"),
    PRODUCE_MAX_AMOUNT_LIMIT (5030110, "单笔投资不可高于最大可投金额"),
    PRODUCE_NEW_USER_LIMIT (5030111, "非新手，无法购买新手标！"),
    PRODUCE_TITLE_EXIST(5030112, "产品名称已存在！"),

    PRODUCE_TOTAL_MAX_AMOUNT_LIMIT (5030113, "累计投资不可高于最大可投金额"),


    // 产品 后台状态
    PRODUCE_REPAYMENT_NOT_EXIST (5030301, "产品还款记录不存在！"),
    PRODUCE_REPAYMENT_STATUS_ERROR (5030302, "产品还款状态不予许还款！"),

    PRODUCE_REPAYMENT_DATE_ERROR (5030303, "产品还没到还款时间，无法还款！"),
    PRODUCE_STATUS_NOT_REPAYMENT (5030304, "产品状态应为还款中，无法还款！"),
    PRODUCE_SELL_OUT_STATUS_NOT_ON_SALE (5030305, "产品状态非销售中，无法开始计息！"),
    PRODUCE_NOT_SELL_OUT (5030306, "产品还没满标，无法开始计息！"),
    PRODUCE_EXPIRE_STATUS_NOT_INTEREST (5030307, "产品状态非计息中，无法开始还款中！"),

    PRODUCE_AMOUNT_MAX_LT_AMOUNT_MIN (5030308, "产品起购金额应小于封顶金额！"),
    PRODUCE_AMOUNT_MIN_TO_LITTLE (5030309, "产品起购金额应大于 100 ！"),
    PRODUCE_AMOUNT_MIN_MULTIPLE (5030310, "产品起购金额应为 100 的整数倍！"),
    PRODUCE_AMOUNT_MAX_MULTIPLE_AMOUNT_MIN (5030311, "产品封顶金额应为起购金额的整数倍！"),

    //优惠券
    COUPON_NOT_EXIST (5040101, "优惠券不存在！"),
    COUPON_DISABLE (5040102, "优惠券不可用！"),
    
    //投资
    INVESTMENT_CHANNEL_NOT_SUPPORT(5050101, "不支持该投资通道"),

    // 广告
    ADVERTISEMENT_NAVIGATION_MAX(5060101, "导航最多 5 个保持上架状态，请先下架其他导航，在上架！"),
    
    ;

    private Integer status;

    private String msg;

    private ApiStatusEnum(int status, String desc){
        this.status = status;
        this.msg = desc;
    }

    public static String getMsgByStatus(int status){
        for(ApiStatusEnum refer : ApiStatusEnum.values()) {
            if (refer.getStatus()==status) {
                return refer.getMsg();
            }
        }
        return null;
    }
    
    public static ApiStatusEnum fromCode(int status){
        for(ApiStatusEnum refer : ApiStatusEnum.values()) {
            if (refer.getStatus()==status) {
                return refer;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
