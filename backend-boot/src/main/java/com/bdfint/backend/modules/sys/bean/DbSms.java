package com.bdfint.backend.modules.sys.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "db_sms")
public class DbSms extends BaseIntEntity<DbSms>{
    private String sendCardId;

    private String sendMobile;

    private String toCardId;

    private String toMobile;

    private String type;

    private String content;

    private Date createTime;

    private String userId;

    private String status;

    public String getSendCardId() {
        return sendCardId;
    }

    public void setSendCardId(String sendCardId) {
        this.sendCardId = sendCardId == null ? null : sendCardId.trim();
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile == null ? null : sendMobile.trim();
    }

    public String getToCardId() {
        return toCardId;
    }

    public void setToCardId(String toCardId) {
        this.toCardId = toCardId == null ? null : toCardId.trim();
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile == null ? null : toMobile.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Transient
    private String startAt;
    @Transient
    private String endAt;
    @Transient
    private String cardId;
    @Transient
    private String mobile;
    @Transient
    private String ymd;

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }
}