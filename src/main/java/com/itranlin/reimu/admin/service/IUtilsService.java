package com.itranlin.reimu.admin.service;

import com.itranlin.reimu.admin.vo.source.PresignedUrl;

public interface IUtilsService {
    PresignedUrl generateUploadPresignedUrl(String fileName, String md5, String fileType);
    String generatePreviewPresignedUrl(String valueId);

}
