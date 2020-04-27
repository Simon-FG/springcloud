package com.bdfint.backend.framework.common;

/**
 * 公共参数接收器
 *
 */
public class Param extends BaseEntity<Param> {

    private String ids;

    private String checkedIds;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCheckedIds() {
        return checkedIds;
    }

    public void setCheckedIds(String checkedIds) {
        this.checkedIds = checkedIds;
    }
}
