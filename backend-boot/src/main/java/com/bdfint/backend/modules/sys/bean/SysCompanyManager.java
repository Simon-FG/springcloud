package com.bdfint.backend.modules.sys.bean;


import javax.persistence.Table;
import javax.persistence.Transient;

import com.bdfint.backend.framework.common.BaseIntEntity;

import java.util.Date;

@Table( name = "sys_user_certified")
public class SysCompanyManager extends BaseIntEntity<SysCompanyManager>{
//	private Integer id;

    private String company;

    private String address;

    private String corporation;

    private String telphone;

    private String accountBank;

    private String account;

    private String texpayerCode;

    private String handIdcardImg;

    private String businessLicenceImg;

    private String confirm;

    private String website;

    private String poster;

    private Date regTime;

    private String regUserid;

    private String realName;

    private String idNomber;

    private String uploadIdFile1;

    private String uploadIdFile2;

    private String cause;

    @Transient
    private String fileChars; // a:id正 b:id反 c:手持证件照 d:营业执照

    public String getFileChars() {
        return fileChars;
    }

    public void setFileChars(String fileChars) {
        this.fileChars = fileChars;
    }

    @Transient
    private String confirmNum; // 0:未认证  5:个人认证  6:企业认证

    public String getConfirmNum() {
        return confirmNum;
    }

    public void setConfirmNum(String confirmNum) {
        this.confirmNum = confirmNum;
    }
    //    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation == null ? null : corporation.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank == null ? null : accountBank.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getTexpayerCode() {
        return texpayerCode;
    }

    public void setTexpayerCode(String texpayerCode) {
        this.texpayerCode = texpayerCode == null ? null : texpayerCode.trim();
    }

    public String getHandIdcardImg() {
        return handIdcardImg;
    }

    public void setHandIdcardImg(String handIdcardImg) {
        this.handIdcardImg = handIdcardImg;
    }

    public String getBusinessLicenceImg() {
        return businessLicenceImg;
    }

    public void setBusinessLicenceImg(String businessLicenceImg) {
        this.businessLicenceImg = businessLicenceImg == null ? null : businessLicenceImg.trim();
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm == null ? null : confirm.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster == null ? null : poster.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRegUserid() {
        return regUserid;
    }

    public void setRegUserid(String regUserid) {
        this.regUserid = regUserid == null ? null : regUserid.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNomber() {
        return idNomber;
    }

    public void setIdNomber(String idNomber) {
        this.idNomber = idNomber;
    }

    public String getUploadIdFile1() {
        return uploadIdFile1;
    }

    public void setUploadIdFile1(String uploadIdFile1) {
        this.uploadIdFile1 = uploadIdFile1;
    }

    public String getUploadIdFile2() {
        return uploadIdFile2;
    }

    public void setUploadIdFile2(String upLoadIdFile2) {
        this.uploadIdFile2 = upLoadIdFile2;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}