package com.bdfint.backend.modules.gis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.DbNorthLocation;

@Mapper
@Repository
@TargetDataSource("pg")
public interface DbNorthLocationMapper extends CommonPgMapper<DbNorthLocation>{
    
	List<DbNorthLocation> queryByParamNorthLocation(DbNorthLocation record);

	int getDaySum(DbNorthLocation location);
	
	int getPositionReportNum(List list);

	List<DbNorthLocation> getList(HashMap map);

	List<DbNorthLocation> getList1(DbNorthLocation location);
}