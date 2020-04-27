package com.bdfint.backend.framework.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * entity基类
 *
 */
public abstract class BasePgEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Transient
    protected Integer pageNum = 1;
    @Transient
    protected Integer pageSize;
    @Transient
    protected String orderBy;

    protected String delFlag = "0";    // 删除标记（0：正常；1：删除；2：审核）


    public BasePgEntity() {

    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";
}
