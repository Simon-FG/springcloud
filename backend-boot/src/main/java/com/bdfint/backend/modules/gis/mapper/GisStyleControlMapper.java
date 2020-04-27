package com.bdfint.backend.modules.gis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisStyleControl;

@Mapper
@Repository
public interface GisStyleControlMapper extends CommonPgMapper<GisStyleControl>{

    int delBatch(Integer ... id);
    
    void insertStyleControl(GisStyleControl record);
    
    GisStyleControl queryById(GisStyleControl record);
}