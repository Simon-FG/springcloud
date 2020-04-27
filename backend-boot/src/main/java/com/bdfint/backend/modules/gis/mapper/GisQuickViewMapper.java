package com.bdfint.backend.modules.gis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisQuickView;

@Mapper
@Repository
public interface GisQuickViewMapper extends CommonPgMapper<GisQuickView>{

    int delBatch(Integer ... id);
    
    GisQuickView queryById(Integer id);
    
    List<GisQuickView> queryByUserIdAndWhetherOpen(GisQuickView record);
    
    void insertQuickView(GisQuickView record);
    
    List<GisQuickView> queryByUserIdAndInit(GisQuickView record);
}