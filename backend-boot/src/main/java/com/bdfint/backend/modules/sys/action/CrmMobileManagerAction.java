package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.service.CrmMobileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsl on 2018/1/25.
 *
 * ----------接口-----------
 *      添加手机号（String ... mobile）
 *      http://localhost:8082/admin/addMobile
 *      删除手机号（Integer ... id）
 *      http://localhost:8082/admin/delMobile
 *
 *
 */
@RestController
@RequestMapping("/mobile")
public class CrmMobileManagerAction extends BaseAction {

    @Autowired
    private CrmMobileManagerService crmMobileManagerService;

    @RequestMapping("/addMobile")
    public Boolean addMobile(String ... mobile) throws Exception {
        return crmMobileManagerService.addMobile(mobile);
    }

    @RequestMapping("/delMobile")
    public Boolean delMobile(Integer ... id) throws Exception {
        return crmMobileManagerService.delMobile(id);
    }
}
