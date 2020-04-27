package com.dbs.model;

public class SysYjSon {
    private Integer yjgnId;

    private String yjgnName;

    private String yjgnContent;

    private String yjgnImg;

    private Integer parentId;

    public Integer getYjgnId() {
        return yjgnId;
    }

    public void setYjgnId(Integer yjgnId) {
        this.yjgnId = yjgnId;
    }

    public String getYjgnName() {
        return yjgnName;
    }

    public void setYjgnName(String yjgnName) {
        this.yjgnName = yjgnName == null ? null : yjgnName.trim();
    }

    public String getYjgnContent() {
        return yjgnContent;
    }

    public void setYjgnContent(String yjgnContent) {
        this.yjgnContent = yjgnContent == null ? null : yjgnContent.trim();
    }

    public String getYjgnImg() {
        return yjgnImg;
    }

    public void setYjgnImg(String yjgnImg) {
        this.yjgnImg = yjgnImg == null ? null : yjgnImg.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}