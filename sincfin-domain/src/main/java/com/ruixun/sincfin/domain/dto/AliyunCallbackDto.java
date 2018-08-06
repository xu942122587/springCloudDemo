package com.ruixun.sincfin.domain.dto;

public class AliyunCallbackDto implements java.io.Serializable {

    private static final long serialVersionUID = -7703652103838448017L;
    // 文件桶
    private String bucket;
    // 文件名
    private String object;
    // 文件类型
    private String mimeType;
    // 文件大小
    private Long size;
    // 文件唯一标识（关联键）
    private String fileKey;
    // 业务类型
    private String category;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
