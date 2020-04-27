package com.bdfint.backend.modules.sys.bean;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table( name = "sys_hardware_item")
public class SysHardwareItem {
    private Integer itemId;

    private String itemType;

    private String name;

    private String byname;

    private String describe;

    private BigDecimal price;

    private String hardwareImg;

    private Date updateTime;

    private String updateBy;

    private String menuId;

    private String status;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getByname() {
        return byname;
    }

    public void setByname(String byname) {
        this.byname = byname == null ? null : byname.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getHardwareImg() {
        return hardwareImg;
    }

    public void setHardwareImg(String hardwareImg) {
        this.hardwareImg = hardwareImg == null ? null : hardwareImg.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}