package com.itranlin.reimu.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("sys_video")
@ApiModel(value = "SysVideo对象", description = "")
public class SysVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("视频名字")
    private String name;

    @ApiModelProperty("视频标签")
    private String code;

    @ApiModelProperty("文件id")
    private String sourceId;

    @TableField(insertStrategy = FieldStrategy.NEVER,updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String createBy;

    @ApiModelProperty("简介")
    private String summary;
    @ApiModelProperty("是否删除")
    @TableLogic
    private Boolean deleted;
}
