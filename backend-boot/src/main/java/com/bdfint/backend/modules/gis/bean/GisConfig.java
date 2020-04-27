package com.bdfint.backend.modules.gis.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;

public class GisConfig extends BaseIntEntity<GisConfig>{

    private String userId;

    private String iocColor;

    private String fontColor;

    private Integer fontSize;

    private String fontEdgeColor;

    private String isLabelAll;

    private String isLabelBeidou;

    private String labelBeidouPerfix;

    private String labelBeidouSuffix;

    private String isLabelBiaoji;

    private String labelBiaojiPerfix;

    private String labelBiaojiSuffix;

    private String isLabelJiwei;

    private String labelJiweiPerfix;

    private String labelJiweiSuffix;

    private String isLabelXhlx;

    private String labelXhlxPerfix;

    private String labelXhlxSuffix;

    private String isLabelH;

    private String labelHPerfix;

    private String labelHSuffix;

    private String isLabelV;

    private String labelVPerfix;

    private String labelVSuffix;

    private String isLabelD;

    private String labelDPerfix;

    private String labelDSuffix;

    private String isLabelS;

    private String labelSPerfix;

    private String labelSSuffix;

    private Double centerLon;

    private Double centerLat;

    private Integer zoomLevel;

    private String mapBgcloor;

    private Integer angle;

    private Integer trackDisplayTime;

    private String isShow;

    private String showPerfix;

    private String showSuffix;

    private String bdPointColor;

    private String g4PointColor;

    private String isAutoPlay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIocColor() {
        return iocColor;
    }

    public void setIocColor(String iocColor) {
        this.iocColor = iocColor == null ? null : iocColor.trim();
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor == null ? null : fontColor.trim();
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontEdgeColor() {
        return fontEdgeColor;
    }

    public void setFontEdgeColor(String fontEdgeColor) {
        this.fontEdgeColor = fontEdgeColor == null ? null : fontEdgeColor.trim();
    }

    public String getIsLabelAll() {
        return isLabelAll;
    }

    public void setIsLabelAll(String isLabelAll) {
        this.isLabelAll = isLabelAll == null ? null : isLabelAll.trim();
    }

    public String getIsLabelBeidou() {
        return isLabelBeidou;
    }

    public void setIsLabelBeidou(String isLabelBeidou) {
        this.isLabelBeidou = isLabelBeidou == null ? null : isLabelBeidou.trim();
    }

    public String getLabelBeidouPerfix() {
        return labelBeidouPerfix;
    }

    public void setLabelBeidouPerfix(String labelBeidouPerfix) {
        this.labelBeidouPerfix = labelBeidouPerfix == null ? null : labelBeidouPerfix.trim();
    }

    public String getLabelBeidouSuffix() {
        return labelBeidouSuffix;
    }

    public void setLabelBeidouSuffix(String labelBeidouSuffix) {
        this.labelBeidouSuffix = labelBeidouSuffix == null ? null : labelBeidouSuffix.trim();
    }

    public String getIsLabelBiaoji() {
        return isLabelBiaoji;
    }

    public void setIsLabelBiaoji(String isLabelBiaoji) {
        this.isLabelBiaoji = isLabelBiaoji == null ? null : isLabelBiaoji.trim();
    }

    public String getLabelBiaojiPerfix() {
        return labelBiaojiPerfix;
    }

    public void setLabelBiaojiPerfix(String labelBiaojiPerfix) {
        this.labelBiaojiPerfix = labelBiaojiPerfix == null ? null : labelBiaojiPerfix.trim();
    }

    public String getLabelBiaojiSuffix() {
        return labelBiaojiSuffix;
    }

    public void setLabelBiaojiSuffix(String labelBiaojiSuffix) {
        this.labelBiaojiSuffix = labelBiaojiSuffix == null ? null : labelBiaojiSuffix.trim();
    }

    public String getIsLabelJiwei() {
        return isLabelJiwei;
    }

    public void setIsLabelJiwei(String isLabelJiwei) {
        this.isLabelJiwei = isLabelJiwei == null ? null : isLabelJiwei.trim();
    }

    public String getLabelJiweiPerfix() {
        return labelJiweiPerfix;
    }

    public void setLabelJiweiPerfix(String labelJiweiPerfix) {
        this.labelJiweiPerfix = labelJiweiPerfix == null ? null : labelJiweiPerfix.trim();
    }

    public String getLabelJiweiSuffix() {
        return labelJiweiSuffix;
    }

    public void setLabelJiweiSuffix(String labelJiweiSuffix) {
        this.labelJiweiSuffix = labelJiweiSuffix == null ? null : labelJiweiSuffix.trim();
    }

    public String getIsLabelXhlx() {
        return isLabelXhlx;
    }

    public void setIsLabelXhlx(String isLabelXhlx) {
        this.isLabelXhlx = isLabelXhlx == null ? null : isLabelXhlx.trim();
    }

    public String getLabelXhlxPerfix() {
        return labelXhlxPerfix;
    }

    public void setLabelXhlxPerfix(String labelXhlxPerfix) {
        this.labelXhlxPerfix = labelXhlxPerfix == null ? null : labelXhlxPerfix.trim();
    }

    public String getLabelXhlxSuffix() {
        return labelXhlxSuffix;
    }

    public void setLabelXhlxSuffix(String labelXhlxSuffix) {
        this.labelXhlxSuffix = labelXhlxSuffix == null ? null : labelXhlxSuffix.trim();
    }

    public String getIsLabelH() {
        return isLabelH;
    }

    public void setIsLabelH(String isLabelH) {
        this.isLabelH = isLabelH == null ? null : isLabelH.trim();
    }

    public String getLabelHPerfix() {
        return labelHPerfix;
    }

    public void setLabelHPerfix(String labelHPerfix) {
        this.labelHPerfix = labelHPerfix == null ? null : labelHPerfix.trim();
    }

    public String getLabelHSuffix() {
        return labelHSuffix;
    }

    public void setLabelHSuffix(String labelHSuffix) {
        this.labelHSuffix = labelHSuffix == null ? null : labelHSuffix.trim();
    }

    public String getIsLabelV() {
        return isLabelV;
    }

    public void setIsLabelV(String isLabelV) {
        this.isLabelV = isLabelV == null ? null : isLabelV.trim();
    }

    public String getLabelVPerfix() {
        return labelVPerfix;
    }

    public void setLabelVPerfix(String labelVPerfix) {
        this.labelVPerfix = labelVPerfix == null ? null : labelVPerfix.trim();
    }

    public String getLabelVSuffix() {
        return labelVSuffix;
    }

    public void setLabelVSuffix(String labelVSuffix) {
        this.labelVSuffix = labelVSuffix == null ? null : labelVSuffix.trim();
    }

    public String getIsLabelD() {
        return isLabelD;
    }

    public void setIsLabelD(String isLabelD) {
        this.isLabelD = isLabelD == null ? null : isLabelD.trim();
    }

    public String getLabelDPerfix() {
        return labelDPerfix;
    }

    public void setLabelDPerfix(String labelDPerfix) {
        this.labelDPerfix = labelDPerfix == null ? null : labelDPerfix.trim();
    }

    public String getLabelDSuffix() {
        return labelDSuffix;
    }

    public void setLabelDSuffix(String labelDSuffix) {
        this.labelDSuffix = labelDSuffix == null ? null : labelDSuffix.trim();
    }

    public String getIsLabelS() {
        return isLabelS;
    }

    public void setIsLabelS(String isLabelS) {
        this.isLabelS = isLabelS == null ? null : isLabelS.trim();
    }

    public String getLabelSPerfix() {
        return labelSPerfix;
    }

    public void setLabelSPerfix(String labelSPerfix) {
        this.labelSPerfix = labelSPerfix == null ? null : labelSPerfix.trim();
    }

    public String getLabelSSuffix() {
        return labelSSuffix;
    }

    public void setLabelSSuffix(String labelSSuffix) {
        this.labelSSuffix = labelSSuffix == null ? null : labelSSuffix.trim();
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(Double centerLat) {
        this.centerLat = centerLat;
    }

    public Integer getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(Integer zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public String getMapBgcloor() {
        return mapBgcloor;
    }

    public void setMapBgcloor(String mapBgcloor) {
        this.mapBgcloor = mapBgcloor == null ? null : mapBgcloor.trim();
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

	public Integer getTrackDisplayTime() {
		return trackDisplayTime;
	}

	public void setTrackDisplayTime(Integer trackDisplayTime) {
		this.trackDisplayTime = trackDisplayTime;
	}

	public Double getCenterLon() {
		return centerLon;
	}

	public void setCenterLon(Double centerLon) {
		this.centerLon = centerLon;
	}

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getShowPerfix() {
        return showPerfix;
    }

    public void setShowPerfix(String showPerfix) {
        this.showPerfix = showPerfix;
    }

    public String getShowSuffix() {
        return showSuffix;
    }

    public void setShowSuffix(String showSuffix) {
        this.showSuffix = showSuffix;
    }

    public String getBdPointColor() {
        return bdPointColor;
    }

    public void setBdPointColor(String bdPointColor) {
        this.bdPointColor = bdPointColor;
    }

    public String getG4PointColor() {
        return g4PointColor;
    }

    public void setG4PointColor(String g4PointColor) {
        this.g4PointColor = g4PointColor;
    }

    public String getIsAutoPlay() {
        return isAutoPlay;
    }

    public void setIsAutoPlay(String isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
    }
}