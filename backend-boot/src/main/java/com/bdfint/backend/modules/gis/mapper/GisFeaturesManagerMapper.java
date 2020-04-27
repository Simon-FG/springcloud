package com.bdfint.backend.modules.gis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisFeaturesManager;

@Mapper
@Repository
public interface GisFeaturesManagerMapper extends CommonPgMapper<GisFeaturesManager>{
    
    int delBatch(Integer ... id);
    
    void insertGisFeaturesManager(GisFeaturesManager record);
    
    List<GisFeaturesManager> queryBylayerIdFeaturesManager(GisFeaturesManager record);
}