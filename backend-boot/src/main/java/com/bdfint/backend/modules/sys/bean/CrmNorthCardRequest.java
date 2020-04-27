package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name="crm_north_card_request")
public class CrmNorthCardRequest extends BaseIntEntity<CrmNorthCardRequest>{
	
	private static final long serialVersionUID = 1L;
	
    private String requestId;//申请ID(用户ID+日期+顺序号)

    private String cardType;//北斗卡类型（1民用普通卡、2民用智慧卡）

    private String cardLeven;//北斗卡通讯等级（1-4级）

    private String content;//用途

    private String relations;//是否建立通播（1是、0否）

    private String usageMode;//使用方式

    private String frequentncy;//使用频度

    private String usageArea;//使用区域

    private String reportNumber;//检测报告编号

    private String reportFile;//检测报告上传地址

    private String contractFile;//用户合同

    private String aircraftFile;//航空器信息表

    private String usageUnit;//使用单位

    private Integer total;//北斗卡购买数量

    private String cardLife;//北斗卡运营年限

    private Date startTime;//申请时间
    
    private String status;//北斗卡申请状态（1申请/2受理/3完成/0退回）

    private String userId;//申请人ID
    
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardLeven() {
        return cardLeven;
    }

    public void setCardLeven(String cardLeven) {
        this.cardLeven = cardLeven == null ? null : cardLeven.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRelations() {
        return relations;
    }

    public void setRelations(String relations) {
        this.relations = relations == null ? null : relations.trim();
    }

    public String getUsageMode() {
        return usageMode;
    }

    public void setUsageMode(String usageMode) {
        this.usageMode = usageMode == null ? null : usageMode.trim();
    }

    public String getFrequentncy() {
        return frequentncy;
    }

    public void setFrequentncy(String frequentncy) {
        this.frequentncy = frequentncy == null ? null : frequentncy.trim();
    }

    public String getUsageArea() {
        return usageArea;
    }

    public void setUsageArea(String usageArea) {
        this.usageArea = usageArea == null ? null : usageArea.trim();
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber == null ? null : reportNumber.trim();
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile == null ? null : reportFile.trim();
    }

    public String getContractFile() {
        return contractFile;
    }

    public void setContractFile(String contractFile) {
        this.contractFile = contractFile == null ? null : contractFile.trim();
    }

    public String getAircraftFile() {
        return aircraftFile;
    }

    public void setAircraftFile(String aircraftFile) {
        this.aircraftFile = aircraftFile == null ? null : aircraftFile.trim();
    }

    public String getUsageUnit() {
        return usageUnit;
    }

    public void setUsageUnit(String usageUnit) {
        this.usageUnit = usageUnit == null ? null : usageUnit.trim();
    }

    public String getCardLife() {
        return cardLife;
    }

    public void setCardLife(String cardLife) {
        this.cardLife = cardLife == null ? null : cardLife.trim();
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
		this.status = status;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

    public Long getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(Long fullPrice) {
		this.fullPrice = fullPrice;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
    
}