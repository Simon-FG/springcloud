package com.dbs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class SysJjfa {
    private Integer faId;

    private String faName;

    private String faContent;
    
    private String faGs;

    private String faBanner;

    private String faIco;

    private Date updateTime;

    private String updateBy;

    private String menuId;

    private List<SysJjfaSon> sysJjfaSonList;
    @Transient
    private Integer fatdId;
    @Transient
    private String fatdName;
    @Transient
    private String fatdContent;
    @Transient
    private String fatdIco;
    @Transient
    private Integer parentId;

    public List<SysJjfaSon> getSysJjfaSonList() {
        return sysJjfaSonList;
    }

//    public SysJjfa() {
//    }

//    public SysJjfa(Integer faId, String faName, String faContent, String faBanner, String faIco, Date fbTime, Date updateTime, Integer menuId) {
//        this.faId = faId;
//        this.faName = faName;
//        this.faContent = faContent;
//        this.faBanner = faBanner;
//        this.faIco = faIco;
//        this.fbTime = fbTime;
//        this.updateTime = updateTime;
//        this.menuId = menuId;
//    }

    public void setSysJjfaSonList(List<SysJjfaSon> sysJjfaSonList) {
        this.sysJjfaSonList = sysJjfaSonList;
    }

    public Integer getFaId() {
        return faId;
    }

    public void setFaId(Integer faId) {
        this.faId = faId;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName == null ? null : faName.trim();
    }

    public String getFaContent() {
        return faContent;
    }

    public void setFaContent(String faContent) {
        this.faContent = faContent == null ? null : faContent.trim();
    }

    public String getFaBanner() {
        return faBanner;
    }

    public void setFaBanner(String faBanner) {
        this.faBanner = faBanner == null ? null : faBanner.trim();
    }

    public String getFaIco() {
        return faIco;
    }

    public void setFaIco(String faIco) {
        this.faIco = faIco == null ? null : faIco.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

	public String getFaGs() {
		return faGs;
	}

	public void setFaGs(String faGs) {
		this.faGs = faGs;
	}

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
		this.fatdName = fatdName;
	}

	public String getFatdContent() {
		return fatdContent;
	}

	public void setFatdContent(String fatdContent) {
		this.fatdContent = fatdContent;
	}

	public String getFatdIco() {
		return fatdIco;
	}

	public void setFatdIco(String fatdIco) {
		this.fatdIco = fatdIco;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
    
}