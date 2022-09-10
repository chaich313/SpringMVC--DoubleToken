package com.itranlin.reimu.admin.dto.dic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ayo
 **/
@Data
@ApiModel("字典项提交数据")
public class DicCommitDTO {
    private String id;

    private String name;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "父关系id")
    private String parentIds;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty("排序")
    private Integer theSort;

    @ApiModelProperty("是否允许删除")
    private Boolean allowDelete;

}
