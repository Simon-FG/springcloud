package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneManager;
import com.bdfint.backend.modules.sys.bean.Dict;
import com.bdfint.backend.modules.sys.service.CrmAirplaneManagerService;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2018/1/16.
 *
 * -------接口---------
 *      注册申请
 *      http://localhost:8082/admin/airplaneManager/register
 *      根据tailCode获取一个airplaneManager实体
 *      http://localhost:8082/admin/airplaneManager/getOneByTailCode
 *      分页列表（tailCode、type、status）
 *      http://localhost:8082/admin/airplaneManager/findListByPage
 *
 *      获取状态列表
 *      http://localhost:8082/useradmin/airplaneManager/getStatusList
 *      
 *      航空器编辑接口(1修改成功/0未找到对应的数据)
 *      airplaneManager/editAirplane
 */
@RestController
@RequestMapping("{adminPath}/airplaneManager")
public class CrmAirplaneManagerAction extends BaseAction{

    @Autowired
    private CrmAirplaneManagerService crmAirplaneManagerService;

    @RequestMapping("/register")
    public Boolean register(CrmAirplaneManager crmAirplaneManager) throws Exception {
        return crmAirplaneManagerService.register(crmAirplaneManager);
    }

    @RequestMapping("/getOneByTailCode")
    public CrmAirplaneManager getOneByTailCode(String tailCode){
        return crmAirplaneManagerService.getOneByTailCode(tailCode);
    }

    @RequestMapping("/findListByPage")
    public PageInfo<CrmAirplaneManager> findListByPage(CrmAirplaneManager crmAirplaneManager) throws Exception {
        return crmAirplaneManagerService.findListByPage(crmAirplaneManager);
    }

    @RequestMapping("/getStatusList")
    public List<Dict> getStatusList(){
        return DictUtils.getDictList("airplane_manager_status");
    }
    
    @RequestMapping("/editAirplane")
    public int editAirplane(CrmAirplaneManager crmAirplaneManager) throws Exception {
		return crmAirplaneManagerService.editAirplane(crmAirplaneManager);
    	
    }
}
