package com.bdfint.backend.modules.sys.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;


import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "crm_question_answer")
public class CrmQuestionAnswer extends BaseIntEntity<CrmQuestionAnswer>{

    private static final long serialVersionUID = 1L;

    private String type;

    private String content;

    private String status;

    private String feedback;

    private String userBy;

    private Date startTime;

    private Date endTime;

    private String userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    public String getUserBy() {
        return userBy;
    }

    public void setUserBy(String userBy) {
        this.userBy = userBy == null ? null : userBy.trim();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Transient
    //@OneToMany(cascade = CascadeType.ALL,targetEntity = CrmQuestionAnswerImg.class)
    private List<CrmQuestionAnswerImg> imgs;

    public List<CrmQuestionAnswerImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<CrmQuestionAnswerImg> imgs) {
        this.imgs = imgs;
    }

    //----------以下字段用做查询，封装在该实体下----------
    @Transient
    private String startAt; //查询开始时间 （此处定义为String类型，可省去String 与 Date之间的自定义Converter的添加）
    @Transient
    private String endAt; //查询结束时间

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}