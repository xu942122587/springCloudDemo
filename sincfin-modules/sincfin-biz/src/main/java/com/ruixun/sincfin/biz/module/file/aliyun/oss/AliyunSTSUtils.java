package com.ruixun.sincfin.biz.module.file.aliyun.oss;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p> aliyun 获取临时令牌工具类 </p>
 *
 * @author qj  2018-06-01
 * @version v1.0
 */
public class AliyunSTSUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunSTSUtils.class);

    private static AliyunSTSUtils aliyunSTSUtils = new AliyunSTSUtils();

    public AssumeRoleResponse response;


    private AliyunSTSUtils() {

    }

    public static AliyunSTSUtils getSingle() {
        return aliyunSTSUtils;
    }

    public AssumeRoleResponse build() {

//        Properties properties = getProperties("application-sts.yml");

/*
        if (response != null)
            return response;
*/

        try {
            // Init ACS ClientQ
            DefaultProfile.addEndpoint("", "", "Sts", AliyunSTSConfig.getEndpoint());

            IClientProfile profile = DefaultProfile.getProfile("", AliyunOSSConfig.getAccessKeyId(), AliyunOSSConfig.getAccessKeySecret());

            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(AliyunSTSConfig.getRoleArn());
            request.setRoleSessionName(AliyunSTSConfig.getRoleSessionName());
            request.setPolicy(AliyunSTSConfig.getPolicy()); // Optional
            request.setDurationSeconds(Long.parseLong(AliyunSTSConfig.getDurationSeconds()));
            response = client.getAcsResponse(request);

            return response;
        } catch (ClientException e) {
            LOGGER.error("join up aliyun OSS get STS error code [] and massage [] and reaquestId []", e.getErrCode(), e.getErrMsg(), e.getRequestId());
            e.printStackTrace();
            return response;
        }finally {
        }
    }

    /**
     * <p> 读取 properties文件 </p>
     *
     * @param filePath
     * @return
     */
    public static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try {
            InputStream in = AliyunSTSUtils.class.getClassLoader().getResourceAsStream(filePath);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
