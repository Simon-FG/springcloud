package com.bdfint.backend.modules.gis.bean;


import com.bdfint.backend.framework.common.BasePgEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "db_date_sum")
public class DbDateSum extends BasePgEntity<DbDateSum> {
    @Id
    private Long id;

    private String cardId;

    private String type;

    private String msgType;

    private Integer total;

    private Date startTime;

    private Date endTime;

    /**
     * 上报，连续10分钟未上传，视为离线（单位分钟）
     */
//    private int offLineTime = 10;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

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

//    public int getOffLineTime() {
//        return offLineTime;
//    }
//
//    public void setOffLineTime(int offLineTime) {
//        this.offLineTime = offLineTime;
//    }
}