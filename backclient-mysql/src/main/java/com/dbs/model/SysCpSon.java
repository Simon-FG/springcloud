package com.dbs.model;

public class SysCpSon {
    private Integer cpgnId;

    private String cpgnName;

    private String cpgnContent;

    private String cpgnIco;

    private Integer parentId;

    public Integer getCpgnId() {
        return cpgnId;
    }

    public void setCpgnId(Integer cpgnId) {
        this.cpgnId = cpgnId;
    }

    public String getCpgnName() {
        return cpgnName;
    }

    public void setCpgnName(String cpgnName) {
        this.cpgnName = cpgnName == null ? null : cpgnName.trim();
    }

    public String getCpgnContent() {
        return cpgnContent;
    }

    public void setCpgnContent(String cpgnContent) {
        this.cpgnContent = cpgnContent == null ? null : cpgnContent.trim();
    }

    public String getCpgnIco() {
        return cpgnIco;
    }

    public void setCpgnIco(String cpgnIco) {
        this.cpgnIco = cpgnIco == null ? null : cpgnIco.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}