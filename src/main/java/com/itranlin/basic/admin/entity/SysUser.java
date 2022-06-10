package com.itranlin.basic.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author CY
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String realName;

    private String password;

    private String avatar;

    private String type;

    private LocalDateTime createTime;
}
