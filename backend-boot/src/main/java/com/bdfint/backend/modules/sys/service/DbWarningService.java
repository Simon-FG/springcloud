package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.DbWarning;
import com.github.pagehelper.PageInfo;

/**
 * Created by lsl on 2018/1/24.
 */
public interface DbWarningService extends BaseIntService<com.bdfint.backend.modules.sys.bean.DbWarning> {

    public PageInfo<DbWarning> getPage5(DbWarning dbWarning) throws Exception;
}
