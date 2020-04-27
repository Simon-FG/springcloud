package com.dbs.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class SysYj {
    private Integer yjId;

    private String yjName;

    private String yjBm;

    private String yjMs;

    private BigDecimal yjPrice;

    private String yjImg;

    private Date updateTime;

    private String menuId;

    private String itemType;

    private String updateBy;
    
    private List<SysYjSon> sysYjSonList;
    @Transient
    private Integer yjgnId;
    @Transient
    private String yjgnName;
    @Transient
    private String yjgnContent;
    @Transient
    private String yjgnImg;
    @Transient
    private Integer parentId;

    public Integer getYjId() {
        return yjId;
    }

    public void setYjId(Integer yjId) {
        this.yjId = yjId;
    }

    public String getYjName() {
        return yjName;
    }

    public void setYjName(String yjName) {
        this.yjName = yjName == null ? null : yjName.trim();
    }

    public String getYjBm() {
        return yjBm;
    }

    public void setYjBm(String yjBm) {
        this.yjBm = yjBm == null ? null : yjBm.trim();
    }

    public String getYjMs() {
        return yjMs;
    }

    public void setYjMs(String yjMs) {
        this.yjMs = yjMs == null ? null : yjMs.trim();
    }

    public BigDecimal getYjPrice() {
        return yjPrice;
    }

    public void setYjPrice(BigDecimal yjPrice) {
        this.yjPrice = yjPrice;
    }

    public String getYjImg() {
        return yjImg;
    }

    public void setYjImg(String yjImg) {
        this.yjImg = yjImg == null ? null : yjImg.trim();
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public List<SysYjSon> getSysYjSonList() {
		return sysYjSonList;
	}

	public void setSysYjSonList(List<SysYjSon> sysYjSonList) {
		this.sysYjSonList = sysYjSonList;
	}

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
		this.yjgnName = yjgnName;
	}

	public String getYjgnContent() {
		return yjgnContent;
	}

	public void setYjgnContent(String yjgnContent) {
		this.yjgnContent = yjgnContent;
	}

	public String getYjgnImg() {
		return yjgnImg;
	}

	public void setYjgnImg(String yjgnImg) {
		this.yjgnImg = yjgnImg;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
    
}