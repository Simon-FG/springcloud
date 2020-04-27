package com.bdfint.backend.modules.gis.bean;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_nofly_zone_manager")
public class GisNoflyZoneManager extends BasePgEntity<GisNoflyZoneManager>{
	
	@Id
	private int id;
	//标注名称
    private String labelText;

    //样式id
    private String labelTypeId;

    //空间范围
    private Object geoRange;

    //空域面积
    private Double area;

    //创建时间
    private Date createTime;

    //每周周几可不禁飞
    private String allowWeek;

    //每天那些时间可以
    private String allowHours;

    //1、有效； 2、删除
    private String delFlag;

    //用户ID 
    private String userId;


    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText == null ? null : labelText.trim();
    }

    public String getLabelTypeId() {
        return labelTypeId;
    }

    public void setLabelTypeId(String labelTypeId) {
        this.labelTypeId = labelTypeId == null ? null : labelTypeId.trim();
    }

    public Object getGeoRange() {
        return geoRange;
    }

    public void setGeoRange(Object geoRange) {
        this.geoRange = geoRange;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAllowWeek() {
        return allowWeek;
    }

    public void setAllowWeek(String allowWeek) {
        this.allowWeek = allowWeek == null ? null : allowWeek.trim();
    }

    public String getAllowHours() {
        return allowHours;
    }

    public void setAllowHours(String allowHours) {
        this.allowHours = allowHours == null ? null : allowHours.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
}