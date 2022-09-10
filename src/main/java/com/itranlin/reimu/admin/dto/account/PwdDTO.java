package com.itranlin.reimu.admin.dto.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author itranlin
 */
@Data
@ApiModel("修改密码数据")
public class PwdDTO {

    @NotEmpty(message = "原密码不能为空")
    @ApiModelProperty("原密码")
    private String password;

    @NotEmpty
    @ApiModelProperty("新密码")
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
