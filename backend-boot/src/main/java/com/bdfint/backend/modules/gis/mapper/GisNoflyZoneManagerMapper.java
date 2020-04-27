package com.bdfint.backend.modules.gis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisNoflyZoneManager;

@Mapper
@Repository
public interface GisNoflyZoneManagerMapper extends CommonPgMapper<GisNoflyZoneManager>{

    int delBatch(Integer ... id);
    
    void insertNoflyZone(GisNoflyZoneManager record);
}