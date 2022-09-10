package com.itranlin.reimu.admin.service.impl;

import com.itranlin.reimu.admin.entity.SysSource;
import com.itranlin.reimu.admin.service.ISysSourceService;
import com.itranlin.reimu.admin.service.IUtilsService;
import com.itranlin.reimu.admin.vo.source.PresignedUrl;
import com.itranlin.reimu.common.bean.tencent.CosConfig;
import com.itranlin.reimu.common.handler.BaseContextHandler;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

@Service
public class UtilsService implements IUtilsService {

    @Resource
    private CosConfig cosConfig;

    @Resource
    private COSClient cosClient;

    @Resource
    private ISysSourceService sourceService;

    @Override
    public PresignedUrl generateUploadPresignedUrl(String fileName, String md5, String fileType){
        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
        HttpMethodName method = HttpMethodName.PUT;
        // 填写本次请求的参数，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的参数
        Map<String, String> params = new HashMap<>();

// 填写本次请求的头部，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的头部
        Map<String, String> headers = new HashMap<>();
        SysSource source = sourceService.getByMd5(md5);
        boolean fileExist = cosClient.doesObjectExist(cosConfig.getBucketName(), md5);

        if (fileExist){
            if (null == source){
                source = new SysSource()
                        .setName(fileName)
                        .setCreateBy(BaseContextHandler.getId())
                        .setMd5(md5)
                        .setFileType(fileType)
                        .setCosKey(md5)
                        .setStatus("uploaded");
                sourceService.save(source);
            }else if (!source.getStatus().equals("uploaded")){
                source.setStatus("uploaded");
                sourceService.updateById(source);
            }
            return new PresignedUrl(false,null,source.getId());
        }

        URL url = cosClient.generatePresignedUrl(cosConfig.getBucketName(), DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now())+"/"+md5, expirationDate, method, headers, params);
        if (null == source){
            source = new SysSource()
                    .setName(fileName)
                    .setCreateBy(BaseContextHandler.getId())
                    .setMd5(md5)
                    .setFileType(fileType)
                    .setCosKey(md5)
                    .setStatus("uploading");
            sourceService.save(source);
        }
        return new PresignedUrl(true,url.toString(),source.getId());
    }

    @Override
    public String generatePreviewPresignedUrl(String valueId) {
        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
        HttpMethodName method = HttpMethodName.GET;
        // 填写本次请求的参数，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的参数
        Map<String, String> params = new HashMap<>();

// 填写本次请求的头部，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的头部
        Map<String, String> headers = new HashMap<>();
        SysSource source = sourceService.getById(valueId);
        URL url = cosClient.generatePresignedUrl(cosConfig.getBucketName(), source.getCosKey(), expirationDate, method, headers, params);
        return url.toString();
    }
}
