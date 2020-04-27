package com.bdfint.backend.modules.sys.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

@Table(name = "crm_north_card")
public class CrmNorthCard extends BaseIntEntity<CrmNorthCard>{
    private String cardId;

    private String cardType;

    private String cardVersion;

    private String cardLeven;

//    private String owner;
    private String appPurpose;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String lon;

    private String lat;

    private String alt;

    private String angle;

    private String locationType;

    private String parentCardId;

    private String broadcostCode;

    private String status;

    private String note;

    private Date noteTime;

    private String orderId;

    private String userId;

    private String usageMode;

    private String frequentncy;

    private String usageArea;

    private Integer price;//单价

    private Integer operatePrice;//运营费用
    
    private String bound;//是否已绑定（0默认未绑定状态，1已绑定）

    private String setPlaneColor;

    private String tag;

    private Date locUpdateTime;

    @Transient
    private String tailCode;

    @Transient
    private String type;

    @Transient
    private Integer num; //申请卡数量


    public Date getLocUpdateTime() {
        return locUpdateTime;
    }

    public void setLocUpdateTime(Date locUpdateTime) {
        this.locUpdateTime = locUpdateTime;
    }
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(String cardVersion) {
        this.cardVersion = cardVersion;
    }

    public String getCardLeven() {
        return cardLeven;
    }

    public void setCardLeven(String cardLeven) {
        this.cardLeven = cardLeven == null ? null : cardLeven.trim();
    }

//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner == null ? null : owner.trim();
//    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt == null ? null : alt.trim();
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle == null ? null : angle.trim();
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType == null ? null : locationType.trim();
    }

    public String getParentCardId() {
        return parentCardId;
    }

    public void setParentCardId(String parentCardId) {
        this.parentCardId = parentCardId == null ? null : parentCardId.trim();
    }

    public String getBroadcostCode() {
        return broadcostCode;
    }

    public void setBroadcostCode(String broadcostCode) {
        this.broadcostCode = broadcostCode == null ? null : broadcostCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(Date noteTime) {
        this.noteTime = noteTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUsageMode() {
        return usageMode;
    }

    public void setUsageMode(String usageMode) {
        this.usageMode = usageMode;
    }

    public String getFrequentncy() {
        return frequentncy;
    }

    public void setFrequentncy(String frequentncy) {
        this.frequentncy = frequentncy;
    }

    public String getUsageArea() {
        return usageArea;
    }

    public void setUsageArea(String usageArea) {
        this.usageArea = usageArea;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOperatePrice() {
        return operatePrice;
    }

    public void setOperatePrice(Integer operatePrice) {
        this.operatePrice = operatePrice;
    }

	public String getAppPurpose() {
		return appPurpose;
	}

	public void setAppPurpose(String appPurpose) {
		this.appPurpose = appPurpose;
	}

	public String getTailCode() {
		return tailCode;
	}

	public void setTailCode(String tailCode) {
		this.tailCode = tailCode;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getSetPlaneColor() {
		return setPlaneColor;
	}

	public void setSetPlaneColor(String setPlaneColor) {
		this.setPlaneColor = setPlaneColor;
	}
    

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}