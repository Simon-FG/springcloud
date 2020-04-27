package com.bdfint.backend.modules.gis.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BasePgEntity;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;

@Table(name = "db_north_location")
public class DbNorthLocation extends BasePgEntity<DbNorthLocation>{
    private Long id;

    private Short fFlighttype;

    private Object fFifo;

    private Date fTime;
    
    private Object data;

    @Transient
    private String RE;//注册号
    
    @Transient
    private String FN;//航班号
    
    @Transient
    private String HX;//地址码
    
    @Transient
    private String FT;//信号类型

    @Transient
    private String TE;//发送时间
    
    @Transient
    private String startTime;//开始时间
    
    @Transient
    private String endTime;//结束时间

	@Transient
	List<CrmNorthCard> list;

	@Transient
	private int offLineTime = 10;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getfFlighttype() {
        return fFlighttype;
    }

    public void setfFlighttype(Short fFlighttype) {
        this.fFlighttype = fFlighttype;
    }

    public Object getfFifo() {
        return fFifo;
    }

    public void setfFifo(Object fFifo) {
        this.fFifo = fFifo;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getRE() {
		return RE;
	}

	public void setRE(String rE) {
		RE = rE;
	}

	public String getFN() {
		return FN;
	}

	public void setFN(String fN) {
		FN = fN;
	}

	public String getHX() {
		return HX;
	}

	public void setHX(String hX) {
		HX = hX;
	}

	public String getFT() {
		return FT;
	}

	public void setFT(String fT) {
		FT = fT;
	}

	public String getTE() {
		return TE;
	}

	public void setTE(String TE) {
		this.TE = TE;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<CrmNorthCard> getList() {
		return list;
	}

	public void setList(List<CrmNorthCard> list) {
		this.list = list;
	}

	public int getOffLineTime() {
		return offLineTime;
	}

	public void setOffLineTime(int offLineTime) {
		this.offLineTime = offLineTime;
	}
}