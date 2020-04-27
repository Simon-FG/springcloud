package com.bdfint.backend.modules.gis.mapper;

import java.util.List;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisDistanceRingManager;

public interface GisDistanceRingManagerMapper extends CommonPgMapper<GisDistanceRingManager>{
    
	int delBatch(Integer ... id);
	
	void insertDistanceRing(GisDistanceRingManager record);
	
	List<GisDistanceRingManager> queryByParamDistanceRing(GisDistanceRingManager record);
	
}