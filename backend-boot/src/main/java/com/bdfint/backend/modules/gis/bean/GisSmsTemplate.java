package com.bdfint.backend.modules.gis.bean;

import com.bdfint.backend.framework.common.BasePgEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "gis_sms_template")
public class GisSmsTemplate extends BasePgEntity<GisSmsTemplate>{
    @Id
    private Short id;

    private String title;

    private String content;

    private Date createTime;

    private Short pId;

    @Transient
    private List<GisSmsTemplate> list;

    @Transient
    private String pTitle;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getpId() {
        return pId;
    }

    public void setpId(Short pId) {
        this.pId = pId;
    }

    public List<GisSmsTemplate> getList() {
        return list;
    }

    public void setList(List<GisSmsTemplate> list) {
        this.list = list;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }
}