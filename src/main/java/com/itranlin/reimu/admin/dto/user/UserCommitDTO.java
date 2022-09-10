package com.itranlin.reimu.admin.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author itranlin
 */
@Data
public class UserCommitDTO {

    @ApiModelProperty("ID,修改信息时使用")
    private String id;
    @ApiModelProperty(value = "账号", required = true)
    @Min(value = 6, message = "用户名至少为6位")
    private String username;
    @ApiModelProperty(value = "真实姓名", required = true)
    @NotEmpty(message = "真实姓名不能为空")
    private String realName;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty(value = "角色", allowableValues = "1,2", required = true)
    private String type;
}
