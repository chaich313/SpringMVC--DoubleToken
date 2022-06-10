package com.itranlin.basic.admin.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author itranlin
 */
@Data
@ApiModel(value = "用户信息")
public class UserVO {
    private String id;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("角色")
    private String type;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
