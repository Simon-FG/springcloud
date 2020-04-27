package com.bdfint.backend.modules.gis.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_features_manager")
public class GisFeaturesManager extends BasePgEntity<GisFeaturesManager>{
	
	@Id
	private int id;
	//图层ID
    private String layerId;

    //图层样式id
    private Integer styleId;

    //用户ID
    private String userId;

    //标注
    private String label;

    //备注
    private String remark;

    //地物（保存josn格式）
    private String features;

    //矢量类型
    private String type;
    
    //坐标系
    private String projection;
    
    //每周周几可不禁飞
    private String allowWeek;
    
    //每天那些时间可不禁飞
    private String allowHours;
    
    //哪些天可不禁飞
    private String allowDays;
    
    //0:显示，1隐藏
    private String disable;
    
    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId == null ? null : layerId.trim();
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllowWeek() {
		return allowWeek;
	}

	public void setAllowWeek(String allowWeek) {
		this.allowWeek = allowWeek;
	}

	public String getAllowHours() {
		return allowHours;
	}

	public void setAllowHours(String allowHours) {
		this.allowHours = allowHours;
	}

	public String getAllowDays() {
		return allowDays;
	}

	public void setAllowDays(String allowDays) {
		this.allowDays = allowDays;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}
    
    
}