package com.bdfint.backend.modules.sys.bean;

import com.bdfint.backend.framework.common.BaseIntEntity;

import javax.persistence.Table;

@Table(name = "crm_question_answer_img")
public class CrmQuestionAnswerImg extends BaseIntEntity<CrmQuestionAnswerImg>{

    private String zipImg;

    private String img;

    private Integer parentId;

    public String getZipImg() {
        return zipImg;
    }

    public void setZipImg(String zipImg) {
        this.zipImg = zipImg == null ? null : zipImg.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}