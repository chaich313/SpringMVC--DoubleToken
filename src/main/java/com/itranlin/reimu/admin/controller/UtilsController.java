package com.itranlin.reimu.admin.controller;

import com.itranlin.reimu.admin.service.IUtilsService;
import com.itranlin.reimu.admin.vo.source.PresignedUrl;
import com.itranlin.reimu.common.bean.RequestResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/utils")
public class UtilsController {

    @Resource
    private IUtilsService utilsService;
    @GetMapping("tencent/presigned-url/upload")
    @ResponseBody
    public RequestResult<PresignedUrl> generateUploadPresignedUrl(String fileName, String md5, String fileType){
        return RequestResult.e(utilsService.generateUploadPresignedUrl(fileName,md5,fileType));
    }
    @GetMapping("tencent/presigned-url/preview")
    public RequestResult<String> generatePreviewPresignedUrl(String valueId){
        return RequestResult.e(utilsService.generatePreviewPresignedUrl(valueId));
    }
}
