package com.itranlin.reimu.admin.vo.user;

import com.itranlin.reimu.admin.bo.BaseUser;
import com.itranlin.reimu.common.bean.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author itranlin
 */
@Data
@ApiModel(value = "用户信息")
public class UserVO implements BaseUser {
    private String id;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @Override
    public String getType() {
        return Constants.USER_TYPE.SYSTEM;
    }
}
