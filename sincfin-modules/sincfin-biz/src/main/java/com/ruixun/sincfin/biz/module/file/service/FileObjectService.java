package com.ruixun.sincfin.biz.module.file.service;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.google.common.collect.Maps;
import com.ruixun.sincfin.biz.feign.client.FileObjectClient;
import com.ruixun.sincfin.biz.module.file.aliyun.oss.AliyunOSSConfig;
import com.ruixun.sincfin.biz.module.file.aliyun.oss.AliyunSTSUtils;
import com.ruixun.sincfin.biz.module.file.config.FileObjectHelper;
import com.ruixun.sincfin.biz.module.file.mapper.FileObjectMapper;
import com.ruixun.sincfin.common.util.*;
import com.ruixun.sincfin.domain.dto.FileObjectDto;
import com.ruixun.sincfin.domain.dto.SecurityTokenDto;
import com.ruixun.sincfin.domain.entity.FileObjectEntity;
import com.ruixun.sincfin.domain.query.FileObjectQuery;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("fileObject")
public class FileObjectService implements FileObjectClient {

    @Resource
    private FileObjectMapper fileObjectMapper;


    /**
     * <p>get aliyun security AK and Token</p>
     *
     * @param category
     * @return
     */
    @RequestMapping("getSecurityToken")
    public SecurityTokenDto getSecurityToken(@RequestParam("category") String category) {

        AssumeRoleResponse response = AliyunSTSUtils.getSingle().build();

        if (response == null)
            return null;

        SecurityTokenDto securityTokenDto = new SecurityTokenDto();
        securityTokenDto.setAccessKeyId(response.getCredentials().getAccessKeyId());
        securityTokenDto.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
        securityTokenDto.setSecurityToken(response.getCredentials().getSecurityToken());
        securityTokenDto.setEndpoint(AliyunOSSConfig.getEndpoint());

        securityTokenDto.setCallbackUrl(AliyunOSSConfig.getCallbackHost() + AliyunOSSConfig.CALLBACK_PATH);
        securityTokenDto.setCallbackBodyType(AliyunOSSConfig.CALLBACK_BODY_TYPE);

        securityTokenDto.setCallbackBody(AliyunOSSConfig.CALLBACK_BODY);

        securityTokenDto.setBucket(FileObjectHelper.getBucketByCategory(category));

        Map<String, Object> map = new HashMap<>();
        map.put("x:category", String.valueOf(category));
        securityTokenDto.setCustomAggr(JacksonUtil.toJson(map));
        securityTokenDto.setFileKey(RandomNumUtils.getUUID());
        return securityTokenDto;
    }

    @RequestMapping("aliyunCallback")
    public FileObjectDto aliyunCallback(@RequestBody FileObjectDto fileObjectDto) {

        // todo check call back

        FileObjectEntity fileObjectEntity = new FileObjectEntity();
        BeanHelper.copyProperties(fileObjectEntity, fileObjectDto);
        fileObjectMapper.insertSelective(fileObjectEntity);
        fileObjectDto.setFileUrl(FileObjectHelper.getFileUrl(fileObjectDto.getFileKey(), fileObjectDto.getCategory()));
        return fileObjectDto;
    }


    @RequestMapping("getPublicFileUrl")
    public String getPublicFileUrl(String fileKey) {
        return FileObjectHelper.getPublicFileUrl(fileKey);
    }

    @RequestMapping("getPrivateFileUrl")
    public String getPrivateFileUrl(String fileKey) {
        return FileObjectHelper.getPrivateFileUrl(fileKey);
    }

    @RequestMapping("getPublicFileUrlList")
    public Map<String, String> getPublicFileUrlList(List<String> fileKeyList) {
        Map<String, String> fileUrlMap = Maps.newHashMap();
        fileKeyList.forEach( fileKey -> {
            fileUrlMap.put(fileKey, FileObjectHelper.getPublicFileUrl(fileKey));
        });
        return fileUrlMap;
    }

    @RequestMapping("getPublicFileList")
    public Map<String, FileObjectDto> getPublicFileList(List<String> fileKeyList) {
        FileObjectQuery fileObjectQuery = new FileObjectQuery();
        fileObjectQuery.setFileKeyList(fileKeyList);
        List<FileObjectDto> dtoList = fileObjectMapper.listByCondition(fileObjectQuery);
        Map<String, FileObjectDto> fileObjectDtoMap = Maps.newHashMapWithExpectedSize(dtoList.size());

        dtoList.forEach( dto -> {
            String fileKey = dto.getFileKey();
            dto.setFileUrl(getPublicFileUrl(fileKey));
            fileObjectDtoMap.put(fileKey, dto);
        });
        return fileObjectDtoMap;
    }

//    @PostMapping(value = "/getFileObject")
//    public FileObjectDto getFileObject(@RequestParam(value = "id") Long id) {
//        FileObjectEntity entity = fileObjectMapper.selectByPrimaryKey(id);
//        if (entity == null)
//            return null;
//        FileObjectDto dto = new FileObjectDto();
//        BeanHelper.copyProperties(dto, entity);
//        Properties properties = AliyunOSSUtils.getDefProperties();
//        if (FileObjectBucketEnum.SINCFIN_SERVICE_DEV.getText().equals(dto.getBucket())) {
//            OSSClient client = AliyunOSSUtils.getSingle().build();
//            // 设置URL过期时间30分钟
//            Date expiration = new Date(new Date().getTime() + Integer.parseInt(properties.getProperty("urlTimeOut")));
//            URL url = client.generatePresignedUrl(properties.getProperty("bucketPrivate"), dto.getFullName(), expiration);
//            dto.setFullName(String.valueOf(url));
//            //释放资源
//            AliyunOSSUtils.getSingle().clean();
//            return dto;
//        }
//        String url = properties.getProperty("serviceUrl");
//        StringBuffer buffer = new StringBuffer(url);
//        buffer.append("/");
//        buffer.append(entity.getFullName());
//        dto.setFullName(buffer.toString());
//        return dto;
//    }
}
