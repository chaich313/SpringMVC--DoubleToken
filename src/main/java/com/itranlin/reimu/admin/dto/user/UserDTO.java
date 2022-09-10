package com.itranlin.reimu.admin.dto.user;

import com.itranlin.reimu.admin.dto.SplitPageDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author itranlin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends SplitPageDTO {
    @ApiModelProperty("真实姓名")
    private String name;
}
