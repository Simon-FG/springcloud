package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.sys.bean.CrmMobileManager;
import com.bdfint.backend.modules.sys.service.CrmMobileManagerService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lsl on 2018/1/25.
 */
@Service
public class CrmMobileManagerServiceImpl extends BaseIntServiceImpl<CrmMobileManager> implements CrmMobileManagerService{
    @Override
    public Boolean addMobile(String... mobile) throws Exception {
        if(mobile != null && mobile.length > 0){
            String userId = UserUtils.getUserId();
            Date date = new Date();
            for(String m: mobile){
                CrmMobileManager crmMobileManager = new CrmMobileManager();
                crmMobileManager.setUserId(userId);
                crmMobileManager.setMobile(m);
                crmMobileManager.setRegTime(date);
                insert(crmMobileManager);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean delMobile(Integer ... id) throws Exception {
        if(id != null && id.length > 0) {
            for (Integer i : id) {
                CrmMobileManager crmMobileManager = new CrmMobileManager();
                crmMobileManager.setId(i);
                crmMobileManager.setDelFlag("1");
                update(crmMobileManager);
            }
            return true;
        }
        return false;
    }
}
