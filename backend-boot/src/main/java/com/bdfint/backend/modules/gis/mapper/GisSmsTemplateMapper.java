package com.bdfint.backend.modules.gis.mapper;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisSmsTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GisSmsTemplateMapper extends CommonPgMapper<GisSmsTemplate>{

    int addTemplate(GisSmsTemplate template);

    int delBatch(Integer... id);
}