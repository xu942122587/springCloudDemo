package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by qi on 2018/5/29.
 */
@FeignClient("${biz.feign.name}")
public interface FileObjectClient {

    @RequestMapping(value = "/fileObject/getSecurityToken")
    SecurityTokenDto getSecurityToken(@RequestParam("category") String category);

    @RequestMapping("/fileObject/aliyunCallback")
    FileObjectDto aliyunCallback(@RequestBody FileObjectDto fileObjectDto);

    @RequestMapping("/fileObject/getPublicFileUrl")
    String getPublicFileUrl(String fileKey);

    @RequestMapping("/fileObject/getPublicFileUrlList")
    Map<String, String> getPublicFileUrlList(List<String> fileKeyList);

    @RequestMapping("/fileObject/getPublicFileList")
    Map<String, FileObjectDto> getPublicFileList(List<String> fileKeyList);

//    @RequestMapping(value = "/fileObject/getFileObject")
//    FileObjectDto getFileObject(@RequestParam(value = "id") Long id);
}

