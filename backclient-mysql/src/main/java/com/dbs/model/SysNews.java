package com.dbs.model;

import javax.persistence.Transient;
import java.util.Date;

public class SysNews {
    private Integer newsId;

    private String newsTitle;

    private String newsPath;

    private Date fbTime;

    private String fbStatus;

    private String newsType;

    private String newsContent;

    private String createBy;

    private String groupId;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsPath() {
        return newsPath;
    }

    public void setNewsPath(String newsPath) {
        this.newsPath = newsPath == null ? null : newsPath.trim();
    }

    public Date getFbTime() {
        return fbTime;
    }

    public void setFbTime(Date fbTime) {
        this.fbTime = fbTime;
    }

    public String getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(String fbStatus) {
        this.fbStatus = fbStatus == null ? null : fbStatus.trim();
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType == null ? null : newsType.trim();
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    private int pageNum;

    private int pageSize;

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
}