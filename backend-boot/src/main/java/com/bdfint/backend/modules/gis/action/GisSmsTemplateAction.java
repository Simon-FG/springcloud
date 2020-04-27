package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.gis.bean.GisSmsTemplate;
import com.bdfint.backend.modules.gis.service.GisSmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsl on 2018/4/3.
 *
 *      ---接口---
 *      根据pId查询下级模板列表（Integer pId）
 *      http://localhost:8082/admin/smsTemplate/getTemplateListByPid
 *      根据pId查询下级树形模板列表（Integer pId）
 *      http://localhost:8082/admin/smsTemplate/getTemplateTreeByPid
 *      添加模板（String title、String content）
 *      http://localhost:8082/admin/smsTemplate/addTemplate
 *      删除模板（Integer id）
 *      http://localhost:8082/admin/smsTemplate/delTemplate
 */
@RestController
@RequestMapping("${adminPath}/smsTemplate")
public class GisSmsTemplateAction extends BaseAction {

    @Autowired
    private GisSmsTemplateService templateService;

    @RequestMapping("/getTemplateListByPid")
    private List<GisSmsTemplate> getTemplateList(@RequestParam(required = false,defaultValue = "0") Integer pId){
        return templateService.getTemplateList(pId);
    }

    @RequestMapping("/getTemplateTreeByPid")
    private List<GisSmsTemplate> getTemplateTree(@RequestParam(required = false,defaultValue = "0") Integer pId){
        return templateService.getTemplateTree(pId);
    }

    @RequestMapping("/addTemplate")
    private Boolean addTemplate(GisSmsTemplate template){
        return templateService.addTemplate(template);
    }

    @RequestMapping("/delTemplate")
    private Boolean delTemplate(@RequestParam String id){
        return templateService.delTemplate(id);
    }
}
