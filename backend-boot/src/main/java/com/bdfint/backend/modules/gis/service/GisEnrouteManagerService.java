package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisEnrouteManager;
import com.github.pagehelper.PageInfo;

/**
 * Created by lsl on 2018/5/9.
 */
public interface GisEnrouteManagerService extends BasePgService<GisEnrouteManager> {

    PageInfo<GisEnrouteManager> queryByParamGisEnroute(GisEnrouteManager enrouteManager);
}
