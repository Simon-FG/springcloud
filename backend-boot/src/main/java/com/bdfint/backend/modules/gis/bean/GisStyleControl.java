package com.bdfint.backend.modules.gis.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_style_control")
public class GisStyleControl extends BasePgEntity<GisStyleControl>{
	
	@Id
	private int id;
	//用户ID
    private String userId;

    //图层id
    private Integer layerId;

    //点对象大小
    private Integer pointSize;

    //点颜色16进制方式
    private String pointColor16;

    //点透明度
    private Integer pointOpacity;

    //线宽
    private Integer plineWidth;

    //线型（1实线、2虚线）
    private String plineType;

    //线颜色
    private String plineColor16;

    //线透明度
    private Integer plineOpacity;

    //面颜色
    private String pgonColor;

    //面透明度
    private Integer pgonOpacity;

    //面边线宽度
    private Integer pgonEdgeWidth;

    //面边线颜色
    private String pgonEdgeColor;

    //面边线透明度
    private Integer pgonEdgeOpacity;

    //标注字体大小
    private Integer labelSize;

    //标注背景颜色
    private String labelBgk;

    //标注字体颜色
    private String labelWordColor;

    //标注字体轮廓线颜色
    private String labelWordOColor;

    //标注偏移量x
    private Integer labelOffsetX;

    //标注y方向偏移量
    private Integer labelOffsetY;

    //标注显示样式（1、不显示；2、省略显示；3、换行显示）
    private String labelTextStyle;

    //标注显隐
    private String labelDisplay;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }

    public Integer getPointSize() {
        return pointSize;
    }

    public void setPointSize(Integer pointSize) {
        this.pointSize = pointSize;
    }

    public String getPointColor16() {
        return pointColor16;
    }

    public void setPointColor16(String pointColor16) {
        this.pointColor16 = pointColor16 == null ? null : pointColor16.trim();
    }

    public Integer getPointOpacity() {
        return pointOpacity;
    }

    public void setPointOpacity(Integer pointOpacity) {
        this.pointOpacity = pointOpacity;
    }

    public Integer getPlineWidth() {
        return plineWidth;
    }

    public void setPlineWidth(Integer plineWidth) {
        this.plineWidth = plineWidth;
    }

    public String getPlineType() {
        return plineType;
    }

    public void setPlineType(String plineType) {
        this.plineType = plineType == null ? null : plineType.trim();
    }

    public String getPlineColor16() {
        return plineColor16;
    }

    public void setPlineColor16(String plineColor16) {
        this.plineColor16 = plineColor16 == null ? null : plineColor16.trim();
    }

    public Integer getPlineOpacity() {
        return plineOpacity;
    }

    public void setPlineOpacity(Integer plineOpacity) {
        this.plineOpacity = plineOpacity;
    }

    public String getPgonColor() {
        return pgonColor;
    }

    public void setPgonColor(String pgonColor) {
        this.pgonColor = pgonColor == null ? null : pgonColor.trim();
    }

    public Integer getPgonOpacity() {
        return pgonOpacity;
    }

    public void setPgonOpacity(Integer pgonOpacity) {
        this.pgonOpacity = pgonOpacity;
    }

    public Integer getPgonEdgeWidth() {
        return pgonEdgeWidth;
    }

    public void setPgonEdgeWidth(Integer pgonEdgeWidth) {
        this.pgonEdgeWidth = pgonEdgeWidth;
    }

    public String getPgonEdgeColor() {
        return pgonEdgeColor;
    }

    public void setPgonEdgeColor(String pgonEdgeColor) {
        this.pgonEdgeColor = pgonEdgeColor == null ? null : pgonEdgeColor.trim();
    }

    public Integer getPgonEdgeOpacity() {
        return pgonEdgeOpacity;
    }

    public void setPgonEdgeOpacity(Integer pgonEdgeOpacity) {
        this.pgonEdgeOpacity = pgonEdgeOpacity;
    }

    public Integer getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(Integer labelSize) {
        this.labelSize = labelSize;
    }

    public String getLabelBgk() {
        return labelBgk;
    }

    public void setLabelBgk(String labelBgk) {
        this.labelBgk = labelBgk == null ? null : labelBgk.trim();
    }

    public String getLabelWordColor() {
        return labelWordColor;
    }

    public void setLabelWordColor(String labelWordColor) {
        this.labelWordColor = labelWordColor == null ? null : labelWordColor.trim();
    }

    public String getLabelWordOColor() {
        return labelWordOColor;
    }

    public void setLabelWordOColor(String labelWordOColor) {
        this.labelWordOColor = labelWordOColor == null ? null : labelWordOColor.trim();
    }

    public Integer getLabelOffsetX() {
        return labelOffsetX;
    }

    public void setLabelOffsetX(Integer labelOffsetX) {
        this.labelOffsetX = labelOffsetX;
    }

    public Integer getLabelOffsetY() {
        return labelOffsetY;
    }

    public void setLabelOffsetY(Integer labelOffsetY) {
        this.labelOffsetY = labelOffsetY;
    }

    public String getLabelTextStyle() {
        return labelTextStyle;
    }

    public void setLabelTextStyle(String labelTextStyle) {
        this.labelTextStyle = labelTextStyle == null ? null : labelTextStyle.trim();
    }

	public String getLabelDisplay() {
		return labelDisplay;
	}

	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    
}