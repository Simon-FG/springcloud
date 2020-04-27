package com.bdfint.backend.modules.sys.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "crm_airplane_manager")
public class CrmAirplaneManager extends BaseIntEntity<CrmAirplaneManager>{
    private String tailCode;

    private String type;

    private String madeBy;

    private Integer age;

    private String userId;

    private String status;

    private Date factoryTime;

    private String purpose;

    private String bound;

    private String appUnit;

    private Date regTime;

    private String bdDevice;

    public String getTailCode() {
        return tailCode;
    }

    public void setTailCode(String tailCode) {
        this.tailCode = tailCode == null ? null : tailCode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getFactoryTime() {
        return factoryTime;
    }

    public void setFactoryTime(Date factoryTime) {
        this.factoryTime = factoryTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getAppUnit() {
        return appUnit;
    }

    public void setAppUnit(String appUnit) {
        this.appUnit = appUnit;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getBdDevice() {
        return bdDevice;
    }

    public void setBdDevice(String bdDevice) {
        this.bdDevice = bdDevice;
    }
}