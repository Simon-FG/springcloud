package com.bdfint.backend.modules.gis.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_airline_manager")
public class GisAirlineManager extends BasePgEntity<GisAirlineManager>{

	@Id
	private int id;
	//航路名称
    private String name;

    //用户ID
    private String userId;

    //航线样式id
    private String airlineStyle;

    //航线josn文件
    private String geoJosn;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAirlineStyle() {
        return airlineStyle;
    }

    public void setAirlineStyle(String airlineStyle) {
        this.airlineStyle = airlineStyle == null ? null : airlineStyle.trim();
    }

    public String getGeoJosn() {
        return geoJosn;
    }

    public void setGeoJosn(String geoJosn) {
        this.geoJosn = geoJosn == null ? null : geoJosn.trim();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
}