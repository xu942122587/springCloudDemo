package com.ruixun.sincfin.biz.module.file.aliyun.oss;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruixun.sincfin.common.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by tiantiea on 2018/6/14.
 */
@Component
@RefreshScope
public class AliyunSTSConfig {


    private static String endpoint;

    private static String roleArn;

    private static String roleSessionName;
    private static String durationSeconds;
    private static String policy;


    public static String getEndpoint() {
        return endpoint;
    }

    public static String getRoleArn() {
        return roleArn;
    }

    public static String getRoleSessionName() {
        return roleSessionName;
    }

    public static String getDurationSeconds() {
        return durationSeconds;
    }

    public static String getPolicy() {
        if (StringUtils.isEmpty(policy)) {
            Map<String, Object> policyMap = Maps.newLinkedHashMap();
            policyMap.put("Version", "1");
            List<String> actionList = Lists.newArrayList("oss:ListObjects", "oss:GetObject", "oss:PutObject");
            List<String> resourceList =
                    Lists.newArrayList(
                            "acs:oss:*:*:" + AliyunOSSConfig.getBucketPrivate(),
                            "acs:oss:*:*:" + AliyunOSSConfig.getBucketPrivate() + "/*",
                            "acs:oss:*:*:" + AliyunOSSConfig.getBucketPublic(),
                            "acs:oss:*:*:" + AliyunOSSConfig.getBucketPublic() + "/*");
            Map<String ,Object> statementMap = Maps.newLinkedHashMap();
            statementMap.put("Action", actionList);
            statementMap.put("Resource", resourceList);
            statementMap.put("Effect", "Allow");
            policyMap.put("Statement", Lists.newArrayList(statementMap));

            policy = JacksonUtil.toJson(policyMap);
        }
        return policy;
    }

    @Value("${aliyun.sts.endpoint}")
    public void setEndpoint(String endpoint) {
        AliyunSTSConfig.endpoint = endpoint;
    }

    @Value("${aliyun.sts.roleArn}")
    public void setRoleArn(String roleArn) {
        AliyunSTSConfig.roleArn = roleArn;
    }

    @Value("${aliyun.sts.roleSessionName}")
    public void setRoleSessionName(String roleSessionName) {
        AliyunSTSConfig.roleSessionName = roleSessionName;
    }

    @Value("${aliyun.sts.durationSeconds}")
    public void setDurationSeconds(String durationSeconds) {
        AliyunSTSConfig.durationSeconds = durationSeconds;
    }

}
