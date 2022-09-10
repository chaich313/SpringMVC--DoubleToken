package com.itranlin.reimu.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author itranlin
 * @since 2022-09-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_source")
@ApiModel(value = "SysSource对象")
public class SysSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("文件名")
    private String name;

    @ApiModelProperty("对象存储中的key")
    private String cosKey;

    @ApiModelProperty("文件md5")
    private String md5;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("创建时间")
    @TableField(updateStrategy = FieldStrategy.NEVER,insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty("上传人")
    private String createBy;

    @ApiModelProperty("上传完成时间")
    private LocalDateTime uploadTime;

    @ApiModelProperty("文件类型")
    private String fileType;
}
