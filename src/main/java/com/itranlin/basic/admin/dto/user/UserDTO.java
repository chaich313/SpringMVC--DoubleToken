package com.itranlin.basic.admin.dto.user;

import com.itranlin.basic.admin.dto.SplitPageDTO;
import com.itranlin.basic.admin.entity.SysUser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author itranlin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends SplitPageDTO<SysUser> {
    @ApiModelProperty("真实姓名")
    private String name;
}
