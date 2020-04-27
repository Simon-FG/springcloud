package com.bdfint.backend.modules.sys.bean;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BaseIntEntity;

@Table(name="crm_order_item")
public class CrmOrderItem extends BaseIntEntity<CrmOrderItem>{
	
	private static final long serialVersionUID = 1L;
	
	private String orderId;//订单ID

    private Date createTime;//订单创建时间

    private Long fullPrice;//总价

    private String userName;//购买人

    private Integer goodsId;//所属记录ID

    private String menuId;//菜单ID

    private Integer addressId;//邮寄地址

    private String orderType;//订单类型
    
    private String status;//订单状态

    private Date startTime;//订单生成时间

    private String userId;//用户ID

    private String emsOrder;
    
    private String handleStatus;//处理状态
    
    @Transient
    private String operate;//订单状态信息（时间+操作内容）
    
    @Transient
    private String name;//商品名称
    
    @Transient
    private String itemType;//商品类型（1、北斗卡/2、硬件设备/3、增值服务/4、转网）
    
    @Transient
    private Long price;//单价
    
    @Transient
    private Integer total;//数量
    
    @Transient
    private String address;//邮寄地址
    
    @Transient
    private String postalcode;//邮政编码
    
    @Transient
    private String consignee;//收货人
    
    @Transient
    private String telephone;//电话
    
    @Transient
    private String mobile;//手机号码
    
    @Transient
    private String strTime;//手机号码
    
    @Transient
    private String endTime;//手机号码

    @Transient
    private String img;//图片

    @Transient
    private String flag;//标志（1：handleStatus != 3）

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Long fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getEmsOrder() {
        return emsOrder;
    }

    public void setEmsOrder(String emsOrder) {
        this.emsOrder = emsOrder;
    }

    public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}