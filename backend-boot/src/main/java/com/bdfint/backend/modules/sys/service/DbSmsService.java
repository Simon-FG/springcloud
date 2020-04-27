package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.DbSms;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2018/1/19.
 */
public interface DbSmsService extends BaseIntService<DbSms> {

    public PageInfo<DbSms> findListByPage(DbSms dbSms) throws Exception;

    public Integer getCountTodayByUser();

    public Map<String, Object> getCount(DbSms dbSms);
}
