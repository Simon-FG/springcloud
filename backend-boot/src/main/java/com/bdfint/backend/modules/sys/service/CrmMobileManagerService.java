package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmMobileManager;

/**
 * Created by lsl on 2018/1/25.
 */
public interface CrmMobileManagerService extends BaseIntService<CrmMobileManager> {

    public Boolean addMobile(String ... mobile) throws Exception;

    public Boolean delMobile(Integer ... id) throws Exception;
}
