package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name="crm_sms_request")
public class CrmSmsRequest extends BaseIntEntity<CrmSmsRequest>{
	
	private static final long serialVersionUID = 1L;
	
    private String requestId;//申请ID

    private String smsPackageId;//产品ID

    private Integer total;//购买数量

//    private String cardId;//北斗卡Id

    private Date startTime;//订单提交时间

    private String userId;//用户ID

    private String status;//增值服务申请状态（1申请/2受理/3完成/0退回）
    
    @Transient
    private Long price;//单价

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getSmsPackageId() {
        return smsPackageId;
    }

    public void setSmsPackageId(String smsPackageId) {
        this.smsPackageId = smsPackageId == null ? null : smsPackageId.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

//    public String getCardId() {
//        return cardId;
//    }
//
//    public void setCardId(String cardId) {
//        this.cardId = cardId == null ? null : cardId.trim();
//    }

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
    
}