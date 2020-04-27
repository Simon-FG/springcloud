package com.dbs.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

public class SysCp {
    private Integer cpId;

    private String cpName;

    private String cpJs;

    private BigDecimal cpPrice;

    private String cpMs;

    private String cpBanner;

    private String cpIco;

    private Date updateTime;

    private String menuId;

    private String itemType;

    private String userId;
    
    private String status;
    
    private String showHome;
    
    @Transient
    private Integer cpgnId;
    @Transient
    private String cpgnName;
    @Transient
    private String cpgnContent;
    @Transient
    private String cpgnIco;
    @Transient
    private Integer parentId;

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName == null ? null : cpName.trim();
    }

    public String getCpJs() {
        return cpJs;
    }

    public void setCpJs(String cpJs) {
        this.cpJs = cpJs == null ? null : cpJs.trim();
    }

    public BigDecimal getCpPrice() {
        return cpPrice;
    }

    public void setCpPrice(BigDecimal cpPrice) {
        this.cpPrice = cpPrice;
    }

    public String getCpMs() {
        return cpMs;
    }

    public void setCpMs(String cpMs) {
        this.cpMs = cpMs == null ? null : cpMs.trim();
    }

    public String getCpBanner() {
        return cpBanner;
    }

    public void setCpBanner(String cpBanner) {
        this.cpBanner = cpBanner == null ? null : cpBanner.trim();
    }

    public String getCpIco() {
        return cpIco;
    }

    public void setCpIco(String cpIco) {
        this.cpIco = cpIco == null ? null : cpIco.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
		this.cpgnName = cpgnName;
	}

	public String getCpgnContent() {
		return cpgnContent;
	}

	public void setCpgnContent(String cpgnContent) {
		this.cpgnContent = cpgnContent;
	}

	public String getCpgnIco() {
		return cpgnIco;
	}

	public void setCpgnIco(String cpgnIco) {
		this.cpgnIco = cpgnIco;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShowHome() {
		return showHome;
	}

	public void setShowHome(String showHome) {
		this.showHome = showHome;
	}
	
}