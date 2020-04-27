package com.bdfint.backend.modules.sys.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "crm_mobile_manager")
public class CrmMobileManager extends BaseIntEntity<CrmMobileManager>{
    private String mobile;

    private String userId;

    private Date regTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}