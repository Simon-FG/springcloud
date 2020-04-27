package com.doubleskyline.manage.modules.bd.condition;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SimplePageParam {

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页返回的记录数量")
    @TableField(exist = false)
    private int pageSize = 10;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前查询页数，1起始")
    @TableField(exist = false)
    private int pageNum = 1;


}
