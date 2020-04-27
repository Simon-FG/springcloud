package com.bdfint.backend.modules.gis.bean;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_distance_ring_manager")
public class GisDistanceRingManager extends BasePgEntity<GisDistanceRingManager>{
	
	@Id
	private int id;
	//距离环名称
	private String name;
	
	//用户ID
    private String userId;

    //经度
    private Double lon;

    //纬度
    private Double lat;

    //半径长度（公里）
    private Integer radius;

    //距离环总数
    private Integer count;

    //样式id
    private Integer styleId;

    //创建时间
    private Date createTime;
    
    @Transient
    private GisStyleControl gisStyleControl; 
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GisStyleControl getGisStyleControl() {
		return gisStyleControl;
	}

	public void setGisStyleControl(GisStyleControl gisStyleControl) {
		this.gisStyleControl = gisStyleControl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}