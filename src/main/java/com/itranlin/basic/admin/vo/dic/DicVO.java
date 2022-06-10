package com.itranlin.basic.admin.vo.dic;


import com.itranlin.basic.common.util.tree.node.SourceNode;
import com.itranlin.basic.common.util.tree.node.TargetNode;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ayo
 **/
@Data
public class DicVO implements TargetNode<DicVO, String>, SourceNode<String> {
    private String id;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "父关系")
    private String parentIds;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty("排序")
    private Integer theSort;

    @TableField("is_leaf")
    private Boolean leaf;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否允许删除")
    private Boolean allowDelete;


    @ApiModelProperty(value = "下级分类数组")
    private List<DicVO> children;


    public Integer getSort() {
        return this.sort == null ? 1 : sort;
    }

    public List<DicVO> getChildren() {
        if (null == children) {
            return new ArrayList<>();
        } else {
            return children;
        }
    }

    @Override
    public boolean isLeaf() {
        return null == leaf || leaf;
    }

    @Override
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
}
