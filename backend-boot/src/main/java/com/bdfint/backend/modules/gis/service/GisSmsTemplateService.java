package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisSmsTemplate;

import java.util.List;

/**
 * Created by lsl on 2018/4/3.
 */
public interface GisSmsTemplateService extends BasePgService<GisSmsTemplate> {
    List<GisSmsTemplate> getTemplateList(int pId);

    List<GisSmsTemplate> getTemplateTree(int pId);

    Boolean addTemplate(GisSmsTemplate template);

    Boolean delTemplate(String id);
}
