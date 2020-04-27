package com.doubleskyline.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @Auther: ZZY
 * @date 2019年05月22日
 */
@ApiModel(description = "分页参数")
@Data
public class PageParam implements Serializable {

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    @TableField(exist = false)
    private int pageSize = 10;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    @TableField(exist = false)
    private int pageNum = 1;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    @TableField(exist = false)
    private String orderBy;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式")
    @TableField(exist = false)
    private String orderType;

    /**
     * 分组方式
     */
    @ApiModelProperty(value = "分组方式")
    @TableField(exist = false)
    private String[] groupBy;

    /**
     * 查询字段
     */
    @ApiModelProperty(value = "查询字段")
    @TableField(exist = false)
    private String[] selects;


    public static PageParam firstRowPage(){
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(1);
        return pageParam;
    }
    public static PageParam noLimitPage(){
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(-1);
        return pageParam;
    }
}
