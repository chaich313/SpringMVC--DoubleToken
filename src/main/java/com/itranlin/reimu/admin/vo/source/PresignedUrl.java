package com.itranlin.reimu.admin.vo.source;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresignedUrl {
    private boolean needUpload;
    private String url;
    private String valueId;
}
