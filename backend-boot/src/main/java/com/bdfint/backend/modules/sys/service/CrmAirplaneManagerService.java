package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneManager;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by lsl on 2018/1/16.
 */
public interface CrmAirplaneManagerService extends BaseIntService<CrmAirplaneManager> {

    public Boolean register(CrmAirplaneManager crmAirplaneManager) throws Exception;

    public CrmAirplaneManager getOneByTailCode(String tailCode);

    public PageInfo<CrmAirplaneManager> findListByPage(CrmAirplaneManager crmAirplaneManager) throws Exception;
    
    public int editAirplane(CrmAirplaneManager crmAirplaneManager) throws Exception;

}
