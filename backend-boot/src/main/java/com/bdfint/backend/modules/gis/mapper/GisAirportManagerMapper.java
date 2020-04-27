package com.bdfint.backend.modules.gis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisAirportManager;

@Mapper
@Repository
public interface GisAirportManagerMapper extends CommonPgMapper<GisAirportManager>{
	
    int delBatch(Integer ... id);
    
    void insertAirport(GisAirportManager record);
    
    List<GisAirportManager> queryByParamGisAirport(GisAirportManager record);
}