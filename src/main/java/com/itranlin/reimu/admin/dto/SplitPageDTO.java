package com.itranlin.reimu.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author itranlin
 */
@Data
public class SplitPageDTO {

    @ApiModelProperty(value = "当前页")
    private Integer page;

    @ApiModelProperty(value = "每页数据")
    private Integer pageSize;

    @ApiModelProperty(value = "当前页")
    private List<OrderBy> orderBy;

    public Integer getPage() {
        if (null == page) {
            return 1;
        }
        return page;
    }

    public Integer getPageSize() {
        if (null == pageSize) {
            return 10;
        }
        return pageSize;
    }

}
