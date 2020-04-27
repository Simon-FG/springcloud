package com.bdfint.backend.modules.gis.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_layer_control")
public class GisLayerControl extends BasePgEntity<GisLayerControl>{
	
	@Id
	private int id;
	//图层名称
    private String layerName;

    //图层显示名称
    private String layerLabel;

    //图层类型（字典表：19ecf7f565204734b05dbb706efb5748：XYZ、c595f8d4cdae4a9bba95f20b7cc6d028：矢量）
    private String layerType;

    //用户图层顺序（点类型在上、面类型在下、服务图层最下）
    private Integer layerOrder;

    //图层是否可见（0：不可见、1可见）
    private String layerDisplay;

    //图层样式编号
    private String layerStyleId;

    //用户ID
    private String userId;

    //点聚合距离（单位公里）
    private Integer togetherDist;
    
    //图层组名称（字典表中）
    private String layerGroupId;

    
    public static final String DICT_TYPE = "layer_group_id";
    
    //图层类型
    public static final String DICT_TYPE_LAYER_TYPE = "layerType";
    
    //图层组ID
    public static final String BASICEDITINGLAYERGROUP_ID= "b1f0a22b9acd47e19f74a2cf1265da3e";
    public static final String AIRCRAFTLAYERGROUP_ID= "598c0c226f914359a3b0fec1489407c7";
    public static final String OPERATIONLAYERGROUP_ID= "b08ca528833b42138cf3f1b38e203176";
    public static final String AIRFIELDMAPGROUP_ID= "97cf4acca6244c6095b74116af92a500";
    
    //图层类型  矢量
    public static final String LAYER_TYPE_VECTOR= "c595f8d4cdae4a9bba95f20b7cc6d028";
    
    //图层是否可见  1:可见
    public static final String LAYER_DISPLAY_SO= "1";
    
    
    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName == null ? null : layerName.trim();
    }

    public String getLayerLabel() {
        return layerLabel;
    }

    public void setLayerLabel(String layerLabel) {
        this.layerLabel = layerLabel == null ? null : layerLabel.trim();
    }

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String layerType) {
        this.layerType = layerType == null ? null : layerType.trim();
    }

    public Integer getLayerOrder() {
        return layerOrder;
    }

    public void setLayerOrder(Integer layerOrder) {
        this.layerOrder = layerOrder;
    }

    public String getLayerDisplay() {
        return layerDisplay;
    }

    public void setLayerDisplay(String layerDisplay) {
        this.layerDisplay = layerDisplay == null ? null : layerDisplay.trim();
    }

    public String getLayerStyleId() {
        return layerStyleId;
    }

    public void setLayerStyleId(String layerStyleId) {
        this.layerStyleId = layerStyleId == null ? null : layerStyleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getTogetherDist() {
        return togetherDist;
    }

    public void setTogetherDist(Integer togetherDist) {
        this.togetherDist = togetherDist;
    }

	public String getLayerGroupId() {
		return layerGroupId;
	}

	public void setLayerGroupId(String layerGroupId) {
		this.layerGroupId = layerGroupId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
    
    
}