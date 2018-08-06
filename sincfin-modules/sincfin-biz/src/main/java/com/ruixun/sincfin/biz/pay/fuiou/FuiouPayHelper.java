package com.ruixun.sincfin.biz.pay.fuiou;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.fuiou.mpay.encrypt.DESCoderFUIOU;
import com.fuiou.util.MD5;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruixun.sincfin.biz.config.SincfinConfig;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayRequest;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayResponse;
import com.ruixun.sincfin.common.util.JXMConvertUtil;
import com.ruixun.sincfin.common.util.MapToXMLString;
import com.ruixun.sincfin.common.util.http.HttpUtil;

/**
 * Created by tiantiea on 2018/4/9.
 */
@Component
public class FuiouPayHelper {
	
	/**
	 * 结果不确定的code
	 */
	public static final List<String> unknownCode = Arrays.asList("999998","999999","P000");
	
	/**
	 * 短信验证码错误
	 */
	public static final List<String> smsCodeErrorCode = Arrays.asList("8143");
	
	@Resource
	private SincfinConfig sincfinConfig;

    /**
     * 银行卡信息验证接口说明(五要素)
     *
     * @param fuiouPayRequest bankCard
     * @param fuiouPayRequest name
     * @param fuiouPayRequest idNo
     * @param fuiouPayRequest mobile
     * @return
     */
    public FuiouPayResponse checkCard(long userId,FuiouPayRequest fuiouPayRequest) throws Exception {
        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("MchntCd", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("Ver", SincfinConfig.PAY_FUIOU_API_CHECK_CARD_VERSION);
        apiFmsMap.put("OSsn", fuiouPayRequest.getOSsn());
        apiFmsMap.put("Ono", fuiouPayRequest.getBankCard());
        apiFmsMap.put("Onm", fuiouPayRequest.getName());
        apiFmsMap.put("OCerTp", SincfinConfig.PAY_FUIOU_API_ID_TYPE);
        apiFmsMap.put("OCerNo", fuiouPayRequest.getIdNo());
        apiFmsMap.put("Mno", fuiouPayRequest.getMobile());

        apiFmsMap.put("Sign", buildSign(apiFmsMap, Lists.newArrayList(
                "MchntCd", "Ver", "OSsn", "Ono", "OCerTp", "OCerNo"
        )));

        Map<String, String> postParam = Maps.newHashMap();
        postParam.put("FM", MapToXMLString.converterNotHeader(apiFmsMap, "FM"));

        String responseResult = HttpUtil.RequestForm(sincfinConfig.getPayFuiouApiHost() + SincfinConfig.PAY_FUIOU_API_CHECK_CARD_URL, postParam);
        String jsonString = JXMConvertUtil.XmlConvertJson(responseResult);
        log(userId, jsonString, "银行卡信息验证", fuiouPayRequest.getOSsn());
        Map<String, Object> responseMap = JXMConvertUtil.JsonConvertHashMap(jsonString);
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        fuiouPayResponse.setResponseCode(responseMap.get("Rcd").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RDesc").toString());
        if(responseMap.get("Rcd").toString().equals("0000")){
        	if(checkSign(responseResult)){
                fuiouPayResponse.setBankName(this.bankNameShortName(responseMap.get("Cnm").toString()));
            }else{
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
            }
        }
        
        return fuiouPayResponse;

    }
    
    private boolean checkSign(String response){
    	if(StringUtil.isNotEmpty(response)){
    		String jsonString = JXMConvertUtil.XmlConvertJson(response);
    		Map<String, Object> map = JXMConvertUtil.JsonConvertHashMap(jsonString);
    		String localSgin = buildSign(map, Lists.newArrayList(
                    "Rcd", "OSsn", "CardNo", "MchntCd", "Ver"
    		));
    		String sgin = (String)map.get("Sign");
    		if(localSgin.equals(sgin)){
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * 验证回调 sign
     * @param params
     * @return
     */
    public boolean checkCallback(Map<String, String> params) {
        String sign = params.get("SIGN");
        if (StringUtil.isEmpty(sign)) {
            return false;
        }
        StringBuffer signSb = new StringBuffer();
        signSb.append(params.get("TYPE")).append("|")
                .append(params.get("VERSION")).append("|")
                .append(params.get("RESPONSECODE")).append("|")
                .append(params.get("MCHNTCD")).append("|")
                .append(params.get("MCHNTORDERID")).append("|")
                .append(params.get("ORDERID")).append("|")
                .append(params.get("AMT")).append("|")
                .append(params.get("BANKCARD")).append("|")
                .append(sincfinConfig.getPayFuiouMchntKey());

        return sign.equals(MD5.MD5Encode(signSb.toString()));
    }


    /**
     * 3.1 商户首次协议下单、验证码获取接口
     * 接口说明：进行支付前，商户需先调用此接口。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest mchntOrderId
     * @param fuiouPayRequest userId
     * @param fuiouPayRequest amt
     * @param fuiouPayRequest bankCard
     * @param fuiouPayRequest name
     * @param fuiouPayRequest idNo
     * @param fuiouPayRequest mobile
     * @return
     */
    public FuiouPayResponse orderAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {

        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("USERIP", fuiouPayRequest.getUserIp());
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("TYPE", SincfinConfig.PAY_FUIOU_API_TYPE);
        apiFmsMap.put("MCHNTORDERID", fuiouPayRequest.getMchntOrderId());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());
        apiFmsMap.put("AMT", fuiouPayRequest.getAmt());
        apiFmsMap.put("BANKCARD", fuiouPayRequest.getBankCard());
        apiFmsMap.put("NAME", fuiouPayRequest.getName());
        apiFmsMap.put("IDTYPE", SincfinConfig.PAY_FUIOU_API_ID_TYPE);
        apiFmsMap.put("IDNO", fuiouPayRequest.getIdNo());
        apiFmsMap.put("MOBILE", fuiouPayRequest.getMobile());

        apiFmsMap.put("BACKURL", sincfinConfig.getPayFuiouBackUrl());
        apiFmsMap.put("SIGNTP", SincfinConfig.PAY_FUIOU_API_SIGN_TP);

        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "TYPE", "VERSION", "MCHNTCD", "MCHNTORDERID",
                "USERID", "AMT", "BANKCARD", "BACKURL", "NAME",
                "IDNO", "IDTYPE", "MOBILE", "USERIP"
        )));

        Map<String, Object> responseMap = submitFuiouPayRequest(userId,apiFmsMap, SincfinConfig.PAY_FUIOU_API_ORDER_ACTION_URL,"商户首次协议下单、验证码获取");
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "TYPE", "VERSION", "RESPONSECODE", "MCHNTCD",
                    "MCHNTORDERID", "USERID", "ORDERID", "AMT"
            );
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
        }
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        if(fuiouPayResponse.getResponseCode().equals("0000")){
        	fuiouPayResponse.setOrderId(responseMap.get("ORDERID").toString());
        	fuiouPayResponse.setSignPay(responseMap.get("SIGNPAY").toString());
        }
        return fuiouPayResponse;

    }

    /**
     * 3.2 首次协议支付接口
     * 接口说明:用户填写验证码后调用此接口进行支付。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest mchntOrderId
     * @param fuiouPayRequest userId
     * @param fuiouPayRequest orderId
     * @param fuiouPayRequest bankCard
     * @param fuiouPayRequest mobile
     * @param fuiouPayRequest verCd
     * @param fuiouPayRequest signPay
     * @return
     */
    public FuiouPayResponse PayAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {

        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("USERIP", fuiouPayRequest.getUserIp());
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("TYPE", SincfinConfig.PAY_FUIOU_API_TYPE);
        apiFmsMap.put("MCHNTORDERID", fuiouPayRequest.getMchntOrderId());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());

        apiFmsMap.put("ORDERID", fuiouPayRequest.getOrderId());
        apiFmsMap.put("BANKCARD", fuiouPayRequest.getBankCard());

        apiFmsMap.put("MOBILE", fuiouPayRequest.getMobile());

        apiFmsMap.put("VERCD", fuiouPayRequest.getVerCd());
        apiFmsMap.put("SIGNTP", SincfinConfig.PAY_FUIOU_API_SIGN_TP);

        
        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "TYPE", "VERSION", "MCHNTCD", "ORDERID", "MCHNTORDERID",
                "USERID", "BANKCARD", "VERCD", "MOBILE", "USERIP"
        )));

        apiFmsMap.put("SIGNPAY", fuiouPayRequest.getSignPay());

        Map<String, Object> responseMap = submitFuiouPayRequest(userId,apiFmsMap, SincfinConfig.PAY_FUIOU_API_PAY_ACTION_URL,"首次协议支付");
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "TYPE", "VERSION", "RESPONSECODE", "MCHNTCD",
                    "MCHNTORDERID", "ORDERID", "AMT", "BANKCARD"
            );
     
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
        }
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        fuiouPayResponse.setFirstPay(true);
        if(fuiouPayResponse.getResponseCode().equals("0000")){
        	fuiouPayResponse.setMchntOrderId(responseMap.get("MCHNTORDERID").toString());
            fuiouPayResponse.setOrderId(responseMap.get("ORDERID").toString());
            fuiouPayResponse.setProtocolno(responseMap.get("PROTOCOLNO").toString());
            fuiouPayResponse.setAmt(responseMap.get("AMT").toString());
            fuiouPayResponse.setOrderId(responseMap.get("ORDERID").toString());
            fuiouPayResponse.setSignPay(responseMap.get("SIGNPAY").toString());
        }
        return fuiouPayResponse;

    }

    /**
     * 3.3 商户首次协议下单重发验证码接口
     * 接口说明:用户未收到短信时，可在 180s 后调此接口进行短信重发。重发请求与下单参数同名的参数，值必须保持一致。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest mchntOrderId
     * @param fuiouPayRequest userId
     * @param fuiouPayRequest amt
     * @param fuiouPayRequest bankCard
     * @param fuiouPayRequest name
     * @param fuiouPayRequest idNo
     * @param fuiouPayRequest mobile
     * @return
     */
    public FuiouPayResponse messageAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {

        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("USERIP", fuiouPayRequest.getUserIp());
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("TYPE", SincfinConfig.PAY_FUIOU_API_TYPE);
        apiFmsMap.put("ORDERID", fuiouPayRequest.getOrderId());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());
        apiFmsMap.put("AMT", fuiouPayRequest.getAmt());
        apiFmsMap.put("BANKCARD", fuiouPayRequest.getBankCard());
        apiFmsMap.put("NAME", fuiouPayRequest.getName());
        apiFmsMap.put("IDTYPE", SincfinConfig.PAY_FUIOU_API_ID_TYPE);
        apiFmsMap.put("IDNO", fuiouPayRequest.getIdNo());
        apiFmsMap.put("MOBILE", fuiouPayRequest.getMobile());

        apiFmsMap.put("BACKURL", sincfinConfig.getPayFuiouBackUrl());
        apiFmsMap.put("SIGNTP", SincfinConfig.PAY_FUIOU_API_SIGN_TP);

        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "TYPE", "VERSION", "MCHNTCD", "ORDERID",
                "USERID", "AMT", "BANKCARD", "BACKURL", "NAME",
                "IDNO", "IDTYPE", "MOBILE", "USERIP"
        )));

        Map<String, Object> responseMap = submitFuiouPayRequest(userId , apiFmsMap, SincfinConfig.PAY_FUIOU_API_MESSAGE_ACTION_URL,"商户首次协议下单重发验证码");
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "TYPE", "VERSION", "RESPONSECODE", "MCHNTCD",
                    "MCHNTORDERID", "USERID" ,"ORDERID", "AMT"
            );
            
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
        }
        
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        fuiouPayResponse.setSignPay(responseMap.get("SIGNPAY").toString());
        return fuiouPayResponse;

    }

    /**
     * 3.4 协议下单、验证码获取接口
     * 接口说明:进行支付前，商户需先调用此接口。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest mchntOrderId
     * @param fuiouPayRequest userId
     * @param fuiouPayRequest amt
     * @param fuiouPayRequest protocolNo
     * @return
     */
    public FuiouPayResponse proPayOrderAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {
        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("USERIP", fuiouPayRequest.getUserIp());
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("TYPE", SincfinConfig.PAY_FUIOU_API_TYPE);
        apiFmsMap.put("MCHNTORDERID", fuiouPayRequest.getMchntOrderId());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());
        apiFmsMap.put("AMT", fuiouPayRequest.getAmt());
        apiFmsMap.put("PROTOCOLNO", fuiouPayRequest.getProtocolNo());

        apiFmsMap.put("BACKURL", sincfinConfig.getPayFuiouBackUrl());
        apiFmsMap.put("SIGNTP", SincfinConfig.PAY_FUIOU_API_SIGN_TP);

        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "TYPE", "VERSION", "MCHNTCD", "MCHNTORDERID",
                "USERID", "PROTOCOLNO", "AMT", "BACKURL", "USERIP"
        )));

        Map<String, Object> responseMap = submitFuiouPayRequest(userId , apiFmsMap, SincfinConfig.PAY_FUIOU_API_PRO_PAY_ORDER_ACTION_URL,"协议下单、验证码获取");
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "TYPE", "VERSION", "RESPONSECODE", "MCHNTCD",
                    "MCHNTORDERID", "USERID" ,"ORDERID", "AMT"
            );
            
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
        }
        
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        if(fuiouPayResponse.getResponseCode().equals("0000")){
        	fuiouPayResponse.setMchntOrderId(responseMap.get("MCHNTORDERID").toString());
        	fuiouPayResponse.setUserId(responseMap.get("USERID").toString());
        	fuiouPayResponse.setOrderId(responseMap.get("ORDERID").toString());
        	fuiouPayResponse.setAmt(responseMap.get("AMT").toString());
            fuiouPayResponse.setSignPay(responseMap.get("SIGNPAY").toString());
        }
        return fuiouPayResponse;
    }

    /**
     * 3.5 协议下单重发验证码接口
     * 接口说明:用户未收到短信时，可在 180s 后调此接口进行短信重发。重发请求与下单参数同名的参数，值必须保持一致。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest orderId
     * @param fuiouPayRequest userId
     * @param fuiouPayRequest protocolNo
     * @return
     */
    public FuiouPayResponse proPayMessageAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {
        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("USERIP", fuiouPayRequest.getUserIp());
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("TYPE", SincfinConfig.PAY_FUIOU_API_TYPE);
        apiFmsMap.put("ORDERID", fuiouPayRequest.getOrderId());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());
        apiFmsMap.put("AMT", fuiouPayRequest.getAmt());
        apiFmsMap.put("PROTOCOLNO", fuiouPayRequest.getProtocolNo());

        apiFmsMap.put("BACKURL", sincfinConfig.getPayFuiouBackUrl());
        apiFmsMap.put("SIGNTP", SincfinConfig.PAY_FUIOU_API_SIGN_TP);

        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "TYPE", "VERSION", "MCHNTCD", "ORDERID",
                "USERID", "PROTOCOLNO", "AMT", "BACKURL", "USERIP"
        )));

        Map<String, Object> responseMap = submitFuiouPayRequest(userId , apiFmsMap, SincfinConfig.PAY_FUIOU_API_PRO_PAY_MESSAGE_ACTION_URL,"协议下单重发验证码");
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "TYPE", "VERSION", "RESPONSECODE", "MCHNTCD",
                    "MCHNTORDERID", "USERID" ,"ORDERID", "AMT"
            );
            
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
        }
        
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        fuiouPayResponse.setSignPay(responseMap.get("SIGNPAY").toString());
        return fuiouPayResponse;
    }

    /**
     * 3.6 协议支付接口
     * 接口说明:用户填写验证码后调用此接口进行支付。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest mchntOrderId
     * @param fuiouPayRequest userId
     * @param fuiouPayRequest orderId
     * @param fuiouPayRequest protocolNo
     * @param fuiouPayRequest verCd
     * @return
     */
    public FuiouPayResponse proPayPayAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {
        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("USERIP", fuiouPayRequest.getUserIp());
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("TYPE", SincfinConfig.PAY_FUIOU_API_TYPE);
        apiFmsMap.put("MCHNTORDERID", fuiouPayRequest.getMchntOrderId());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());
        apiFmsMap.put("ORDERID", fuiouPayRequest.getOrderId());
        apiFmsMap.put("PROTOCOLNO", fuiouPayRequest.getProtocolNo());

        apiFmsMap.put("BACKURL", sincfinConfig.getPayFuiouBackUrl());
        apiFmsMap.put("VERCD", fuiouPayRequest.getVerCd());
        apiFmsMap.put("SIGNTP", SincfinConfig.PAY_FUIOU_API_SIGN_TP);
        apiFmsMap.put("SIGNPAY", fuiouPayRequest.getSignPay());
        
        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "TYPE", "VERSION", "MCHNTCD", "ORDERID",
                "MCHNTORDERID", "PROTOCOLNO", "USERID", "VERCD"
        )));
        Map<String, Object> responseMap = submitFuiouPayRequest(userId , apiFmsMap, SincfinConfig.PAY_FUIOU_API_PRO_PAY_PAY_ACTION_URL,"协议支付");
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "TYPE", "VERSION", "RESPONSECODE", "MCHNTCD",
                    "MCHNTORDERID","ORDERID", "AMT","BANKCARD"
            );
            
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
        }
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        
        if(fuiouPayResponse.getResponseCode().equals("0000")){
        	fuiouPayResponse.setMchntOrderId(responseMap.get("MCHNTORDERID").toString());
            fuiouPayResponse.setUserId(responseMap.get("USERID").toString());
        	fuiouPayResponse.setOrderId(responseMap.get("ORDERID").toString());
        	fuiouPayResponse.setAmt(responseMap.get("AMT").toString());
            fuiouPayResponse.setSignPay(responseMap.get("SIGNPAY").toString());
        }
        return fuiouPayResponse;
    }

    /**
     * 3.7 协议解绑接口
     * 接口说明:用户绑定协议卡号只允许协议卡走协议支付，解绑协议卡使用该接口。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest protocolNo
     * @return
     */
    public FuiouPayResponse unbindAction(Long userId , FuiouPayRequest fuiouPayRequest) throws Exception {
        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", SincfinConfig.PAY_FUIOU_API_VERSION);
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("USERID", fuiouPayRequest.getUserId());
        apiFmsMap.put("PROTOCOLNO", fuiouPayRequest.getProtocolNo());

        apiFmsMap.put("BACKURL", sincfinConfig.getPayFuiouBackUrl());
        apiFmsMap.put("VERCD", SincfinConfig.PAY_FUIOU_API_SIGN_TP);

        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "VERSION", "MCHNTCD", "USERID", "PROTOCOLNO"
        )));

        Map<String, String> postParam = Maps.newHashMap();
        postParam.put("FMS", MapToXMLString.converter(apiFmsMap, "REQUEST"));
        
        String responseResult = HttpUtil.RequestForm(sincfinConfig.getPayFuiouApiHost() + SincfinConfig.PAY_FUIOU_API_UNBIND_ACTION_URL, postParam);
        String jsonString = JXMConvertUtil.XmlConvertJson(responseResult);
        
        Map<String, Object> responseMap = JXMConvertUtil.JsonConvertHashMap(jsonString);
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        return fuiouPayResponse;
    }
    
    /**
     * 3.8 订单结果查询接口（商户订单号） 
     * 接口说明:用户绑定协议卡号只允许协议卡走协议支付，解绑协议卡使用该接口。
     *
     * @param fuiouPayRequest userIp
     * @param fuiouPayRequest protocolNo
     * @return
     */
    public FuiouPayResponse queryAction(long userId , FuiouPayRequest fuiouPayRequest) throws Exception {
        Map<Object, Object> apiFmsMap = Maps.newHashMap();

        apiFmsMap.put("VERSION", "2.0");
        apiFmsMap.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        apiFmsMap.put("MCHNTORDERID", fuiouPayRequest.getMchntOrderId());

        apiFmsMap.put("SIGN", buildSign(apiFmsMap, Lists.newArrayList(
                "VERSION", "MCHNTCD", "MCHNTORDERID"
        )));

        Map<String, String> postParam = Maps.newHashMap();
        postParam.put("FM", MapToXMLString.converterNotHeader(apiFmsMap, "ORDER"));
        
        String responseResult = HttpUtil.RequestForm(sincfinConfig.getPayFuiouApiHost() + SincfinConfig.PAY_FUIOU_API_QUERY_ACTION_URL, postParam);
        String jsonString = JXMConvertUtil.XmlConvertJson(responseResult);
        log(userId, jsonString, "订单结果查询", fuiouPayRequest.getMchntOrderId());
        Map<String, Object> responseMap = JXMConvertUtil.JsonConvertHashMap(jsonString);
        
        FuiouPayResponse fuiouPayResponse = new FuiouPayResponse();
        if(responseMap.get("RESPONSECODE").toString().equals("0000")){
        	List<String> checkSignItem = Lists.newArrayList(
                    "VERSION", "RESPONSECODE", "RESPONSEMSG", "MCHNTORDERID"
            );
            
            if(CollectionUtils.isNotEmpty(checkSignItem) && !checkSign(responseMap, checkSignItem)) {
            	
            	fuiouPayResponse.setResponseCode("999997");
                fuiouPayResponse.setResponseMsg("验签失败");
                return fuiouPayResponse;
            }
            
        }
        
        fuiouPayResponse.setResponseCode(responseMap.get("RESPONSECODE").toString());
        fuiouPayResponse.setResponseMsg(responseMap.get("RESPONSEMSG").toString());
        if(fuiouPayResponse.getResponseCode().equals("0000")){
        	fuiouPayResponse.setMchntOrderId(responseMap.get("MCHNTORDERID").toString());
        	fuiouPayResponse.setAmt(responseMap.get("AMT").toString());
        }
        return fuiouPayResponse;
    }

    private Map<String, Object> submitFuiouPayRequest(long userId,Map<Object, Object> apiFmsMap, String url,String apiDesc) throws Exception {
        Map<String, String> postParam = Maps.newHashMap();
        postParam.put("MCHNTCD", sincfinConfig.getPayFuiouMchntCd());
        postParam.put("APIFMS", xmlDesEncrypt(apiFmsMap));
        return submitFuiouRequest(userId,postParam,url,apiDesc);
    }

    // 包装最终的 post param
    private Map<String, Object> submitFuiouRequest(long userId,Map<String, String> postParam, String url,String apiDesc) throws Exception {
        String responseResult = HttpUtil.RequestForm(sincfinConfig.getPayFuiouApiHost() + url, postParam);
        // 解密
        String response = xmlDesDecrypt(responseResult);
        String jsonString = JXMConvertUtil.XmlConvertJson(response);
        String rechargeNo = postParam.get("MCHNTORDERID");
        log(userId, response, apiDesc, rechargeNo);
        return JXMConvertUtil.JsonConvertHashMap(jsonString);
    }



    // 构建 sign 签名
    private String buildSign(@SuppressWarnings("rawtypes") Map apiFmsMap, List<String> orderSignItem) {

        StringBuffer signSb = new StringBuffer();
        for (String item : orderSignItem) {
            signSb.append(apiFmsMap.get(item)).append("|");
        }
        signSb.append(sincfinConfig.getPayFuiouMchntKey());
        return MD5.MD5Encode(signSb.toString());
    }
    

    // 验证签名
    private boolean checkSign(Map<String, Object> responseMap, List<String> checkSignItem) {

        StringBuffer signSb = new StringBuffer();
        for (String item : checkSignItem) {
            signSb.append(responseMap.get(item).toString()).append("|");
        }
        signSb.append(sincfinConfig.getPayFuiouMchntKey());
        String signLocal= MD5.MD5Encode(signSb.toString());
        return signLocal.equals(responseMap.get("SIGN"));
    }

    private String xmlDesEncrypt(Map<Object, Object> xmlMap) throws Exception {
        return DESCoderFUIOU.desEncrypt(MapToXMLString.converter(xmlMap, "REQUEST"),
                DESCoderFUIOU.getKeyLength8(sincfinConfig.getPayFuiouMchntKey()));
    }

    private String xmlDesDecrypt(String response) throws Exception {
        return DESCoderFUIOU.desDecrypt(response, DESCoderFUIOU.getKeyLength8(sincfinConfig.getPayFuiouMchntKey()));
    }
    
    
    private synchronized void log(long userId,String response,String apiDesc,String rechargeNo){
//    	if (StringUtils.isEmpty(response)) {
//            return;
//        }
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        // 异步提交参数到第三方参数表
//        CompletableFuture.supplyAsync(() -> {
//            try {
//            	
//            } catch (Exception e) {
//            }
//            return null;
//        }, executor);
    }
    
    /**
     * 生成富有验证银行卡的request对象
     * @param realName
     * @param idCard
     * @param bankCardNo
     * @param mobile
     * @return
     */
    public FuiouPayRequest bindBankRequest(String realName,String idCard,String bankCardNo,String mobile){
    	FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
    	fuiouPayRequest.setOSsn(System.currentTimeMillis()+"");
    	fuiouPayRequest.setBankCard(bankCardNo);
    	fuiouPayRequest.setName(realName);
    	fuiouPayRequest.setIdNo(idCard);
    	fuiouPayRequest.setMobile(mobile);
    	return fuiouPayRequest;
    }
    
    /**
     * 生成首次协议支付获取验证码request对象
     * @param realName
     * @param idCard
     * @param bankCardNo
     * @param mobile
     * @return
     */
    public FuiouPayRequest orderActionRequest(String userIp,String mchntOrderId,String userId,String amount,String bankCard,String realName,String idCard,String mobile){
    	FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
    	fuiouPayRequest.setUserIp(userIp);
    	fuiouPayRequest.setMchntOrderId(mchntOrderId);
    	fuiouPayRequest.setUserId(userId);
    	fuiouPayRequest.setAmt(amount);
    	fuiouPayRequest.setBankCard(bankCard);
    	fuiouPayRequest.setName(realName);
    	fuiouPayRequest.setIdNo(idCard);
    	fuiouPayRequest.setMobile(mobile);
    	return fuiouPayRequest;
    }
    /**
     * 首次协议支付接口
     * @param userIp
     * @param mchntOrderId
     * @param userId
     * @param orderId
     * @param bankCard
     * @param mobile
     * @param smsCode
     * @return
     */
    public FuiouPayRequest payActionRequest(String userIp,String mchntOrderId,String userId,String orderId,String bankCard,String mobile,String smsCode,String signPay){
    	FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
    	fuiouPayRequest.setUserIp(userIp);
    	fuiouPayRequest.setMchntOrderId(mchntOrderId);
    	fuiouPayRequest.setUserId(userId);
    	fuiouPayRequest.setOrderId(orderId);
    	fuiouPayRequest.setBankCard(bankCard);
    	fuiouPayRequest.setMobile(mobile);
    	fuiouPayRequest.setVerCd(smsCode);
    	fuiouPayRequest.setSignPay(signPay);
    	return fuiouPayRequest;
    	
    }
    
    /**
     * 生成首次协议支付获取验证码request对象
     * @param realName
     * @param idCard
     * @param bankCardNo
     * @param mobile
     * @return
     */
    public FuiouPayRequest proPayOrderActionRequest(String userIp,String mchntOrderId,String userId,String amount,String protocolNo){
    	FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
    	fuiouPayRequest.setUserIp(userIp);
    	fuiouPayRequest.setMchntOrderId(mchntOrderId);
    	fuiouPayRequest.setUserId(userId);
    	fuiouPayRequest.setAmt(amount);
    	fuiouPayRequest.setProtocolNo(protocolNo);
    	return fuiouPayRequest;
    }
    /**
     * 生成协议支付request
     * @param userIp
     * @param mchntOrderId
     * @param userId
     * @param orderId
     * @param protocolNo
     * @param smsCode
     * @param signPay
     * @return
     */
    public FuiouPayRequest proPayPayActionRequest(String userIp,String mchntOrderId,String userId,String orderId,String protocolNo,String smsCode,String signPay){
    	FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
    	fuiouPayRequest.setUserIp(userIp);
    	fuiouPayRequest.setMchntOrderId(mchntOrderId);
    	fuiouPayRequest.setUserId(userId);
    	fuiouPayRequest.setOrderId(orderId);
    	fuiouPayRequest.setProtocolNo(protocolNo);
    	fuiouPayRequest.setVerCd(smsCode);
    	fuiouPayRequest.setSignPay(signPay);
    	return fuiouPayRequest;
    }
    
    
    public String bankNameShortName(String bankName) {
        // 中国工商银行-工商银行
    	if(bankName.contains("（")){
    		bankName = bankName.substring(0, bankName.indexOf("（"));
    	}
        String[] ss = bankName.split("-");
        if (ss.length > 1) {
        	bankName = ss[1];
        }
        if (!bankName.equals("中国银行") && bankName.startsWith("中国")) {
        	bankName = bankName.substring(2);
        }
        if(bankName.contains("分行")){
        	bankName = bankName.substring(0,4);
        }
        return bankName;
    }

}