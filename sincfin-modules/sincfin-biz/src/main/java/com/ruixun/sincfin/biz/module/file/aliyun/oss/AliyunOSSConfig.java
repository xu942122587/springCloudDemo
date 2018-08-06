package com.ruixun.sincfin.biz.module.file.aliyun.oss;

import com.google.common.collect.Maps;
import com.ruixun.sincfin.common.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by tiantiea on 2018/6/13.
 */
@Component
@RefreshScope
public class AliyunOSSConfig {

    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    private static String bucketPrivate;
    private static String bucketPublic;

    private static String bucketPrivateDomain;
    private static String bucketPublicDomain;


    private static String callbackHost;

    private static Integer privateExpiry;

    public final static String CALLBACK_PATH = "/fileObject/aliyunCallback";
    public final static String CALLBACK_BODY_TYPE = "application/json";

//    public final static Map<String, String> CALLBACK_BODY_MAP;
    public final static String CALLBACK_BODY
        = "{\"bucket\":${bucket},\"fileKey\":${object},\"fileLength\":${size},\"contentType\":${mimeType},\"category\":${x:category},\"fullName\":${x:fullName}}";


    static {
//        Map<String, String> callbackMap = Maps.newHashMap();
//        callbackMap.put("bucket", "${bucket}");
//        callbackMap.put("fullName", "${object}");
//        callbackMap.put("size", "${size}");
//        callbackMap.put("contentType", "${mimeType}");
//        callbackMap.put("fileKey", "${x:fileKey}");
//        callbackMap.put("category", "${x:category}");

//        CALLBACK_BODY_MAP = callbackMap;
//        CALLBACK_BODY = JacksonUtil.toJson(callbackMap);
//        CALLBACK_BODY = "{\"bucket\":${bucket},\"object\":${object},\"size\":${size},\"mimeType\":${mimeType},\"fileKey\":${x:fileKey},\"category\":${x:category}}";

    }


    public static String getEndpoint() {
        return endpoint;
    }

    @Value("${aliyun.oss.endpoint}")
    public void setEndpoint(String endpoint) {
        AliyunOSSConfig.endpoint = endpoint;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    @Value("${aliyun.oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        AliyunOSSConfig.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    @Value("${aliyun.oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        AliyunOSSConfig.accessKeySecret = accessKeySecret;
    }

    public static String getBucketPrivate() {
        return bucketPrivate;
    }

    @Value("${aliyun.oss.bucketPrivate}")
    public void setBucketPrivate(String bucketPrivate) {
        AliyunOSSConfig.bucketPrivate = bucketPrivate;
    }

    public static String getBucketPublic() {
        return bucketPublic;
    }

    @Value("${aliyun.oss.bucketPublic}")
    public void setBucketPublic(String bucketPublic) {
        AliyunOSSConfig.bucketPublic = bucketPublic;
    }

    public static String getCallbackHost() {
        return callbackHost;
    }

    @Value("${aliyun.oss.callbackHost}")
    public void setCallbackHost(String callbackHost) {
        AliyunOSSConfig.callbackHost = callbackHost;
    }

    public static String getBucketPrivateDomain() {
        return bucketPrivateDomain;
    }

    @Value("${aliyun.oss.bucketPrivateDomain}")
    public void setBucketPrivateDomain(String bucketPrivateDomain) {
        AliyunOSSConfig.bucketPrivateDomain = bucketPrivateDomain;
    }

    public static String getBucketPublicDomain() {
        return bucketPublicDomain;
    }

    @Value("${aliyun.oss.bucketPublicDomain}")
    public void setBucketPublicDomain(String bucketPublicDomain) {
        AliyunOSSConfig.bucketPublicDomain = bucketPublicDomain;
    }

    public static Integer getPrivateExpiry() {
        return privateExpiry;
    }
    @Value("${aliyun.oss.privateExpiry}")
    public void setPrivateExpiry(Integer privateExpiry) {
        AliyunOSSConfig.privateExpiry = privateExpiry;
    }
}
