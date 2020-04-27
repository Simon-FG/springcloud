package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.gis.bean.GisEnrouteManager;
import com.bdfint.backend.modules.gis.service.GisEnrouteManagerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsl on 2018/5/9.
 *
 *  -----接口------
 *  航路分页列表，右模糊查询（routeid）
 *  admin/enrouteManager/queryByParamGisEnroute
 */
@RestController
@RequestMapping("${adminPath}/enrouteManager")
public class GisEnrouteManagerAction extends BaseAction {
    @Autowired
    private GisEnrouteManagerService enrouteManagerService;

    @RequestMapping("/queryByParamGisEnroute")
    public PageInfo<GisEnrouteManager> queryByParamGisEnroute(GisEnrouteManager enrouteManager){
        return enrouteManagerService.queryByParamGisEnroute(enrouteManager);
    }
}
