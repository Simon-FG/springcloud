package com.bdfint.backend.modules.gis.bean;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BasePgEntity;

@Table(name = "gis_sms")
public class GisSms extends BasePgEntity<GisSms>{
    private Long id;

    private String hexAddress;

    private String content;

    private Date sendTime;

    //0:发送  1:接收
    private String contentType;

    private String cardId;

    private String toCardId;

    private Short msgType;

    @Transient
    private String startTime;//开始时间
    
    @Transient
    private String endTime;//结束时间
    
    //发送消息
    public static String SEND_MSG_TYPE="0";
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHexAddress() {
        return hexAddress;
    }

    public void setHexAddress(String hexAddress) {
        this.hexAddress = hexAddress == null ? null : hexAddress.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getToCardId() {
        return toCardId;
    }

    public void setToCardId(String toCardId) {
        this.toCardId = toCardId == null ? null : toCardId.trim();
    }

    public Short getMsgType() {
        return msgType;
    }

    public void setMsgType(Short msgType) {
        this.msgType = msgType;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}