package com.itranlin.reimu.admin.vo.account;

import com.itranlin.reimu.admin.bo.BaseUser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * @author itranlin
 */
@Data
@ApiModel(value = "登录结果")
public class SignInVO {
    @ApiModelProperty("凭证")
//    private String authorization;

    private String accessToken;
    private String refreshToken;
    private BaseUser userInfo;
}
