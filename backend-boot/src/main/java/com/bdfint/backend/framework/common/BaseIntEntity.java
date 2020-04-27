package com.bdfint.backend.framework.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * entity基类
 *
 */
public abstract class BaseIntEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体编号（唯一标识）
     */
    @Id
    protected int id;
    
    @Transient
    protected int pageNum = 1;
    @Transient
    protected int pageSize;
    @Transient
    protected String orderBy;

    protected String delFlag = "0";    // 删除标记（0：正常；1：删除；2：审核）


    public BaseIntEntity() {

    }

    public BaseIntEntity(int id) {
        this();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
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
