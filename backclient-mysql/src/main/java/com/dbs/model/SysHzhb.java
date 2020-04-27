package com.dbs.model;

public class SysHzhb {
    private Integer hbId;

    private String hbName;

    private String hbLogo;

    private String hbLink;

    public Integer getHbId() {
        return hbId;
    }

    public void setHbId(Integer hbId) {
        this.hbId = hbId;
    }

    public String getHbName() {
        return hbName;
    }

    public void setHbName(String hbName) {
        this.hbName = hbName == null ? null : hbName.trim();
    }

    public String getHbLogo() {
        return hbLogo;
    }

    public void setHbLogo(String hbLogo) {
        this.hbLogo = hbLogo == null ? null : hbLogo.trim();
    }

    public String getHbLink() {
        return hbLink;
    }

    public void setHbLink(String hbLink) {
        this.hbLink = hbLink == null ? null : hbLink.trim();
    }
}