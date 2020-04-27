package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name = "crm_invoice")
public class CrmInvoice extends BaseIntEntity<CrmInvoice>{

	private static final long serialVersionUID = 1L;
	
	private String name;//开票单位

    private String taxpayerNumber;//纳税人识别号

    private String address;//地址

    private String phone;//电话

    private String bankInfo;//开户行及账号

    private String contentInfo;//开票内容

    private Date startTime;//申请时间

    private Date endTime;//办结时间

    private String status;//发票申请状态（1申请中/2已完成/0以驳回）

    private String orderId;//订单ID

    private Integer addressId;//邮寄地址ID

    private String userId;//用户ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber == null ? null : taxpayerNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo == null ? null : bankInfo.trim();
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo == null ? null : contentInfo.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}