package com.itranlin.reimu.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 礼包
 * </p>
 *
 * @author itranlin
 * @since 2022-09-07
 */
@Getter
@Setter
@TableName("sys_gifts")
@ApiModel(value = "SysGifts对象", description = "礼包")
public class SysGifts implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("礼包名")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("生成时间")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty("礼包码数量")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer codeNum;

    @ApiModelProperty("视频数量")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer videoNum;

    @ApiModelProperty("创建人")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String createBy;

    @ApiModelProperty("是否删除")
    @TableLogic
    private Boolean deleted;
}
