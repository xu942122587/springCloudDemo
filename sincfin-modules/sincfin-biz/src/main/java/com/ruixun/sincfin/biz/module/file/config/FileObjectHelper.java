package com.ruixun.sincfin.biz.module.file.config;

import com.aliyun.oss.OSSClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruixun.sincfin.biz.module.file.aliyun.oss.AliyunOSSConfig;
import com.ruixun.sincfin.biz.module.file.aliyun.oss.AliyunOSSUtils;
import com.ruixun.sincfin.biz.module.file.aliyun.oss.AliyunSTSConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 * Created by tiantiea on 2018/6/14.
 */
public class FileObjectHelper {

    public final static String BUCKET_PRIVATE_TYPE = "private";
    public final static String BUCKET_PUBLIC_TYPE = "public";

    private static Map<String, StorageSpace> categoryBucketMap = Maps.newHashMap();

    private static StorageSpace privateStorageSpace = new StorageSpace(AliyunOSSConfig.getBucketPrivate(),
            AliyunOSSConfig.getBucketPrivateDomain(), BUCKET_PRIVATE_TYPE);

    private static StorageSpace publicStorageSpace = new StorageSpace(AliyunOSSConfig.getBucketPublic(),
            AliyunOSSConfig.getBucketPublicDomain(), BUCKET_PUBLIC_TYPE);


    static {

        Lists.newArrayList(FileObjectCategoryEnum.values()).forEach(categoryEnum -> {
            if (categoryEnum.getBucketType().equals(BUCKET_PRIVATE_TYPE)) {
                categoryBucketMap.put(categoryEnum.getCode(), privateStorageSpace);
            } else {
                categoryBucketMap.put(categoryEnum.getCode(), publicStorageSpace);
            }
        });
    }


    public static String getPublicFileUrl(String fileKey) {
        if (StringUtils.isEmpty(fileKey)) {
            return "";
        }
        return publicStorageSpace.getDomain() + "/" + fileKey;

    }

    public static String getPrivateFileUrl(String fileKey) {
        if (StringUtils.isEmpty(fileKey)) {
            return "";
        }
        OSSClient client = AliyunOSSUtils.getSingle().build();
        // 设置URL过期时间30分钟
        Date expiration = DateUtils.addMilliseconds( new Date(), AliyunOSSConfig.getPrivateExpiry());
        URL url = client.generatePresignedUrl(AliyunOSSConfig.getBucketPrivate(), fileKey, expiration);

        //释放资源
        AliyunOSSUtils.getSingle().clean();
        return String.valueOf(url);

    }

    public static String getFileUrl(String fileKey, String category) {
        StorageSpace storageSpace = categoryBucketMap.get(category);

        if (storageSpace == null) {
            storageSpace = publicStorageSpace;
        }
        if (!storageSpace.getType().equals(BUCKET_PRIVATE_TYPE)) {
            return getPublicFileUrl(fileKey);
        }

        return getPrivateFileUrl(fileKey);
    }



    public static String getBucketByCategory(String category) {
        StorageSpace storageSpace = categoryBucketMap.get(category);
        if (storageSpace == null) {
            return AliyunOSSConfig.getBucketPublic();
        }
        return storageSpace.getBucket();
    }



    static class StorageSpace {

        private String bucket;
        private String domain;
        private String type;

        public StorageSpace(String bucket, String domain, String type) {
            this.bucket = bucket;
            this.domain = domain;
            this.type = type;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
