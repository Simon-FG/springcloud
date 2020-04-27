package com.bdfint.backend.modules.gis.bean;

import com.bdfint.backend.framework.util.excel.ExcelField;

/**
 * Created by lsl on 2018/6/5.
 */
public class OnLineSumOut {
    @ExcelField(title = "北斗卡号")
    private String cardId;
    @ExcelField(title = "在线时长")
    private String onLineTime;
    @ExcelField(title = "上报成功率")
    private String successRate;
    @ExcelField(title = "北斗上报时长")
    private String bdTime;
    @ExcelField(title = "4G上报时长")
    private String g4Time;
    @ExcelField(title = "北斗上报数量")
    private String bdNum;
    @ExcelField(title = "4G上报数量")
    private String g4Num;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getOnLineTime() {
        return onLineTime;
    }

    public void setOnLineTime(String onLineTime) {
        this.onLineTime = onLineTime;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getBdTime() {
        return bdTime;
    }

    public void setBdTime(String bdTime) {
        this.bdTime = bdTime;
    }

    public String getG4Time() {
        return g4Time;
    }

    public void setG4Time(String g4Time) {
        this.g4Time = g4Time;
    }

    public String getBdNum() {
        return bdNum;
    }

    public void setBdNum(String bdNum) {
        this.bdNum = bdNum;
    }

    public String getG4Num() {
        return g4Num;
    }

    public void setG4Num(String g4Num) {
        this.g4Num = g4Num;
    }
}
