package com.itranlin.reimu.admin.entity;

import com.itranlin.reimu.common.util.tree.node.SourceNode;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Ayo
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dic")
@ApiModel(value = "数据字典对象")
public class Dic implements SourceNode<String>, Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String parentId;
    private String parentIds;
    private Long createTime;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty("排序")
    private Integer theSort;

    @TableLogic
    private Boolean deleted;

    @ApiModelProperty("是否允许删除")
    private Boolean allowDelete;
}
