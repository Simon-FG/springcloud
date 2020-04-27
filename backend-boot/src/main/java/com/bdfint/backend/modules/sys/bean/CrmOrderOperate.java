package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name="crm_order_operate_log")
public class CrmOrderOperate extends BaseIntEntity<CrmOrderOperate>{
	
	private static final long serialVersionUID = 1L;

    private String orderId;

    private String operate;

    private String operateBy;

    private Date operateTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy == null ? null : operateBy.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}