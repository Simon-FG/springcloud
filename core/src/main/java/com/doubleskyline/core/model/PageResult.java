package com.doubleskyline.core.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @Auther: NPF
 * @date 2016年11月4日 下午12:59:00
 */
@Component
@ApiModel(description = "分页结果")
@Data
@EqualsAndHashCode
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private long totle;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private long pageSize = 10;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private long pages;
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    private long pageNum = 1;
    /**
     * 列表数据
     */
    @ApiModelProperty(value = "列表数据")
    private List<T> list;

    public PageResult() {

    }

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totle 总记录数
     * @param pageSize   每页记录数
     * @param pageNum   当前页数
     */
    public PageResult(List<T> list, long totle, long pageSize, long pageNum) {
        this.list = list;
        this.totle = totle;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.pages = (long) Math.ceil((double) totle / pageSize);
    }

    /**
     * 分页
     */
    public PageResult(IPage<T> page) {
        this.list = page.getRecords();
        this.totle = page.getTotal();
        this.pageSize = page.getSize();
        this.pageNum = page.getCurrent();
        this.pages = page.getPages();
    }

}
