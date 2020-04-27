package com.bdfint.backend.modules.gis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisLayerControl;

@Mapper
@Repository
public interface GisLayerControlMapper extends CommonPgMapper<GisLayerControl>{

    int delBatch(Integer ... id);
    
    List<GisLayerControl> queryBylayerGroupIdLayer(GisLayerControl record);
    
    int updateByIdlayerOrderAndlayerDisplay(GisLayerControl record);
    
    int queryBylayerGroupIdCount(GisLayerControl record);
    
    void insertGisLayerControl(GisLayerControl record);
}