package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmQuestionAnswer;
import com.bdfint.backend.modules.sys.bean.DbWarning;
import com.bdfint.backend.modules.sys.mapper.DbWarningMapper;
import com.bdfint.backend.modules.sys.service.DbWarningService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by lsl on 2018/1/24.
 */
@Service
public class DbWarningServiceImpl extends BaseIntServiceImpl<DbWarning> implements DbWarningService {

    @Autowired
    private DbWarningMapper dbWarningMapper;

    @Override
    public PageInfo<DbWarning> getPage5(DbWarning dbWarning) throws Exception {
        int pageSize = dbWarning.getPageSize() == 0 ? 5 : dbWarning.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(dbWarning.getPageNum(), pageSize);
        }
        dbWarning.setUserId(UserUtils.getUserId());
        List<DbWarning> list = getList(dbWarning);
        PageInfo<DbWarning> page = new PageInfo<>(list);
        page.setPageNum(dbWarning.getPageNum());
        page.setPageSize(pageSize);
        return page;
    }
}
