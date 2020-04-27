package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name="crm_hardware_request")
public class CrmHardwareRequest extends BaseIntEntity<CrmHardwareRequest>{

	private static final long serialVersionUID = 1L;
	
    private String requestId;

    private Integer hardwareItmeId;

    private Integer total;

    private Date startTime;

    private String userId;

    private String status;

    @Transient
    private Long fullPrice;//商品总价

    @Transient
    private Integer parentId;//记录ID

    @Transient
    private String menuId;//菜单ID

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public Integer getHardwareItmeId() {
        return hardwareItmeId;
    }

    public void setHardwareItmeId(Integer hardwareItmeId) {
        this.hardwareItmeId = hardwareItmeId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public Long getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(Long fullPrice) {
		this.fullPrice = fullPrice;
	}

}