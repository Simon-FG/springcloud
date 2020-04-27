package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.bean.DbWarning;
import com.bdfint.backend.modules.sys.service.DbWarningService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsl on 2018/1/24.
 */
@RestController
@RequestMapping("{adminPath}/warning")
public class DbWarningAction extends BaseAction {
    @Autowired
    private DbWarningService dbWarningService;

    @RequestMapping("/getPage5")
    public PageInfo<DbWarning> getPage5(DbWarning dbWarning) throws Exception {
        return dbWarningService.getPage5(dbWarning);
    }
}
