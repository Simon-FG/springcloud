package com.dbs.model;

public class SysJjfaSon {
    private Integer fatdId;

    private String fatdName;

    private String fatdContent;

    private String fatdIco;

    private Integer parentId;

    public Integer getFatdId() {
        return fatdId;
    }

    public void setFatdId(Integer fatdId) {
        this.fatdId = fatdId;
    }

    public String getFatdName() {
        return fatdName;
    }

    public void setFatdName(String fatdName) {
        this.fatdName = fatdName == null ? null : fatdName.trim();
    }

    public String getFatdContent() {
        return fatdContent;
    }

    public void setFatdContent(String fatdContent) {
        this.fatdContent = fatdContent == null ? null : fatdContent.trim();
    }

    public String getFatdIco() {
        return fatdIco;
    }

    public void setFatdIco(String fatdIco) {
        this.fatdIco = fatdIco == null ? null : fatdIco.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}