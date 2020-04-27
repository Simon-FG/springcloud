package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name="crm_airplane_card_bind")
public class CrmAirplaneCardBind extends BaseIntEntity<CrmAirplaneCardBind>{

	private static final long serialVersionUID = 1L;
	
    private String airplaneId;

    private String cardId;

    private Date bindTime;

    private Date unbindTime;

    private String userId;

    private Date startTime;

    private String note;//备注
    
    private String status;//1关联申请、2已关联、0关联变更

    @Transient
    private String tag;//自定义标注
    
    @Transient
    private String num;//返回空闲卡号与机尾号
    
    @Transient
    private String type;//返回类型card\airplane
    
    @Transient
    private String purpose;
    
    @Transient
    private String age;
    
    @Transient
    private String bound;
    
    @Transient
    private String factoryTime;

    @Transient
    private String name;

    @Transient
    private Date cardTime;

    @Transient
    private String setPlaneColor;

    @Transient
    private String company;
    
    
    public String getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId == null ? null : airplaneId.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setUnbindTime(Date unbindTime) {
        this.unbindTime = unbindTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCardTime() {
        return cardTime;
    }

    public void setCardTime(Date cardTime) {
        this.cardTime = cardTime;
    }

    public String getSetPlaneColor() {
        return setPlaneColor;
    }

    public void setSetPlaneColor(String setPlaneColor) {
        this.setPlaneColor = setPlaneColor;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}