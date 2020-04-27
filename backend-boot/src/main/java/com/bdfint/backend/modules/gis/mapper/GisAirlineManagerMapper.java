package com.bdfint.backend.modules.gis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisAirlineManager;

@Mapper
@Repository
public interface GisAirlineManagerMapper extends CommonPgMapper<GisAirlineManager>{

    int delBatch(Integer ... id);
    
    void insertAirline(GisAirlineManager record);
}