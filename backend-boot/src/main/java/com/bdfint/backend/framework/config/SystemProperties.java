/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**s
 */
@Configuration
@ConfigurationProperties
public class SystemProperties {

//    @Value("${spring.mvc.view.prefix}")
//    private String viewProfix;
//    @Value("${spring.mvc.view.suffix}")
//    private String viewSuffix;

    private String fileUploadPath;
    private String fileAccessPath;
    private String staticFileSuffix;
    private String urlSuffix;
    private String adminPath;
    private String frontPath;
    private boolean userKickoutAfter;
    private int userMaxSession;
    private boolean notAllowRefreshIndex;
    private boolean allowStartupEmptyCache;
    private String casServerUrl;
    private String casProjectUrl;

    private String appid;
    private String appkey;
    private String project;
    private String signtype;

    private int offLineTime;
    private int bdInterval;
    private int g4Interval;

//    public String getViewProfix() {
//        return viewProfix;
//    }
//
//    public void setViewProfix(String viewProfix) {
//        this.viewProfix = viewProfix;
//    }
//
//    public String getViewSuffix() {
//        return viewSuffix;
//    }
//
//    public void setViewSuffix(String viewSuffix) {
//        this.viewSuffix = viewSuffix;
//    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getFileAccessPath() {
        return fileAccessPath;
    }

    public void setFileAccessPath(String fileAccessPath) {
        this.fileAccessPath = fileAccessPath;
    }

    public String getStaticFileSuffix() {
        return staticFileSuffix;
    }

    public void setStaticFileSuffix(String staticFileSuffix) {
        this.staticFileSuffix = staticFileSuffix;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

    public String getAdminPath() {
        return adminPath;
    }

    public void setAdminPath(String adminPath) {
        this.adminPath = adminPath;
    }

    public String getFrontPath() {
        return frontPath;
    }

    public void setFrontPath(String frontPath) {
        this.frontPath = frontPath;
    }

    public boolean isUserKickoutAfter() {
        return userKickoutAfter;
    }

    public void setUserKickoutAfter(boolean userKickoutAfter) {
        this.userKickoutAfter = userKickoutAfter;
    }

    public int getUserMaxSession() {
        return userMaxSession;
    }

    public void setUserMaxSession(int userMaxSession) {
        this.userMaxSession = userMaxSession;
    }

    public boolean isNotAllowRefreshIndex() {
        return notAllowRefreshIndex;
    }

    public void setNotAllowRefreshIndex(boolean notAllowRefreshIndex) {
        this.notAllowRefreshIndex = notAllowRefreshIndex;
    }

    public boolean isAllowStartupEmptyCache() {
        return allowStartupEmptyCache;
    }

    public void setAllowStartupEmptyCache(boolean allowStartupEmptyCache) {
        this.allowStartupEmptyCache = allowStartupEmptyCache;
    }

    public String getCasServerUrl() {
        return casServerUrl;
    }

    public void setCasServerUrl(String casServerUrl) {
        this.casServerUrl = casServerUrl;
    }

    public String getCasProjectUrl() {
        return casProjectUrl;
    }

    public void setCasProjectUrl(String casProjectUrl) {
        this.casProjectUrl = casProjectUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSigntype() {
        return signtype;
    }

    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }

    public int getOffLineTime() {
        return offLineTime;
    }

    public void setOffLineTime(int offLineTime) {
        this.offLineTime = offLineTime;
    }

    public int getBdInterval() {
        return bdInterval;
    }

    public void setBdInterval(int bdInterval) {
        this.bdInterval = bdInterval;
    }

    public int getG4Interval() {
        return g4Interval;
    }

    public void setG4Interval(int g4Interval) {
        this.g4Interval = g4Interval;
    }
}