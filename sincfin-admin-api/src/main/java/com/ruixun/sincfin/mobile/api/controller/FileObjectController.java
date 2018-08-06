package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.FileObjectClient;
import com.ruixun.sincfin.domain.dto.FileObjectDto;
import com.ruixun.sincfin.domain.dto.SecurityTokenDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "fileObject")
public class FileObjectController extends BaseController {

    @Resource
    private FileObjectClient fileObjectClient;

    @RequestMapping(value = "getSecurityToken")
    public ApiResponse getSecurityToken(@RequestParam("category") String category) {
        return ApiResponse.successApiResponse(fileObjectClient.getSecurityToken(category));
    }


    @RequestMapping(value = "/aliyunCallback")
    public ApiResponse aliyunCallback(@RequestBody FileObjectDto fileObjectDto) {

        return ApiResponse.successApiResponse(fileObjectClient.aliyunCallback(fileObjectDto));

//        try {
//
//            String ossCallbackBody = AliyunOSSUtils.getCallbackData(request.getInputStream(), Integer.parseInt(request.getHeader("content-length")));
//
//            boolean ret = AliyunOSSUtils.verifyOSSCallbackRequest(request, ossCallbackBody);
//
//            if (!ret)
//                AliyunOSSUtils.response(request, response, "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST);
//
//            AliyunCallbackDto aliyunCallbackDto = JacksonUtil.readValue(ossCallbackBody, AliyunCallbackDto.class);
//
//            if (aliyunCallbackDto != null)
//                fileObjectClient.addUserFileObject(aliyunCallbackDto);
//
//            AliyunOSSUtils.response(request, response, "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

//    /**
//     * s
//     * <p>加载文件接口</p>
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/loadFile")
//    public ApiResponse loadFile(@RequestParam(value = "id", required = true) Long id) {
//
//        return ApiResponse.successApiResponse(fileObjectClient.getFileObject((long) 1));
//    }

}