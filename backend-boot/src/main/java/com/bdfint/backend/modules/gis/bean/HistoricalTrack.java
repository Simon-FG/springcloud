package com.bdfint.backend.modules.gis.bean;

import javax.persistence.Transient;

public class HistoricalTrack {
	
	//北斗卡号
	@Transient
    private String HX;
    
	//经度
	@Transient
    private String LO;
	
	//纬度
	@Transient
    private String LA;
	
	//高度
	@Transient
    private String HE;
	
	//航向
	@Transient
    private String CO;
	
	//速度
	@Transient
    private String SP;
	
	//类型
	@Transient
    private String FT;
	
	//位置报时间
	@Transient
    private String TE;
	
	//接收时间
	@Transient
    private String RT;

	public String getHX() {
		return HX;
	}

	public void setHX(String hX) {
		HX = hX;
	}

	public String getLO() {
		return LO;
	}

	public void setLO(String lO) {
		LO = lO;
	}

	public String getLA() {
		return LA;
	}

	public void setLA(String lA) {
		LA = lA;
	}

	public String getHE() {
		return HE;
	}

	public void setHE(String hE) {
		HE = hE;
	}

	public String getCO() {
		return CO;
	}

	public void setCO(String cO) {
		CO = cO;
	}

	public String getSP() {
		return SP;
	}

	public void setSP(String sP) {
		SP = sP;
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

	public void setTE(String tE) {
		TE = tE;
	}

	public String getRT() {
		return RT;
	}

	public void setRT(String rT) {
		RT = rT;
	}
	
	
}
