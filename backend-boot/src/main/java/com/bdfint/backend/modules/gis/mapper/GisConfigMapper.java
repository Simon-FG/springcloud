package com.bdfint.backend.modules.gis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.gis.bean.GisConfig;

@Mapper
@Repository
public interface GisConfigMapper extends CommonMapper<GisConfig>{

    void insertGisConfig(GisConfig record);

    GisConfig queryByUserIdGisConfig(GisConfig record);
}