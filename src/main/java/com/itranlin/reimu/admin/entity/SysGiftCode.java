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
 * @since 2022-09-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_gift_code")
@ApiModel(value = "SysGiftCode对象", description = "")
public class SysGiftCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String code;

    @ApiModelProperty("礼包ID")
    private String giftId;

    @ApiModelProperty("领取人")
    private String receiveId;

    @ApiModelProperty("领取时间")
    private LocalDateTime receiveTime;

    @TableField(insertStrategy = FieldStrategy.NEVER,updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String createBy;

}
