package com.bdfint.backend.modules.gis.service.impl;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.gis.bean.GisEnrouteManager;
import com.bdfint.backend.modules.gis.mapper.GisEnrouteManagerMapper;
import com.bdfint.backend.modules.gis.service.GisEnrouteManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lsl on 2018/5/9.
 */
@Service
public class GisEnrouteManagerServiceImpl extends BasePgServiceImpl<GisEnrouteManager> implements GisEnrouteManagerService {
    @Autowired
    private GisEnrouteManagerMapper enrouteManagerMapper;

    @Override
    @TargetDataSource("pg")
    public PageInfo<GisEnrouteManager> queryByParamGisEnroute(GisEnrouteManager record) {
        int pageSize = record.getPageSize() == null ? Global.PAGE_SIZE : record.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(record.getPageNum(), pageSize);
        }
        String routeid = record.getRouteid();
        if(StringUtils.isNotBlank(routeid)){
            record.setRouteid(routeid.toUpperCase());
        }
        List<GisEnrouteManager> list = enrouteManagerMapper.queryByParamGisEnroute(record);
        PageInfo<GisEnrouteManager> page = new PageInfo<>(list);
        page.setPageNum(record.getPageNum());
        page.setPageSize(pageSize);
        return page;
    }
}
