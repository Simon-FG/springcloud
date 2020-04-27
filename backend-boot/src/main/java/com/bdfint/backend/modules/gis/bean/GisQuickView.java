package com.bdfint.backend.modules.gis.bean;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_quick_view")
public class GisQuickView extends BasePgEntity<GisQuickView>{
	
	@Id
	private int id;
	
	//用户快视图名称
    private String label;

    //缩放级别
    private Integer zoomLevel;

    //经度
    private Double centerLon;

    //纬度
    private Double centerLat;

    //用户ID
    private String userId;

    //创建时间
    private Date createTime;

    //父ID
    private Integer pId;
    
    //角度
    private Integer angle;
    
    //是否公开
    private String whetherOpen;
    
    //初始化状态
    private String init;
    
    public static final String WHETHER_OPEN_IS="0";
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(Integer zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public Double getCenterLon() {
        return centerLon;
    }

    public void setCenterLon(Double centerLon) {
        this.centerLon = centerLon;
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(Double centerLat) {
        this.centerLat = centerLat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Integer getAngle() {
		return angle;
	}

	public void setAngle(Integer angle) {
		this.angle = angle;
	}

	public String getWhetherOpen() {
		return whetherOpen;
	}

	public void setWhetherOpen(String whetherOpen) {
		this.whetherOpen = whetherOpen;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}
  
    
}