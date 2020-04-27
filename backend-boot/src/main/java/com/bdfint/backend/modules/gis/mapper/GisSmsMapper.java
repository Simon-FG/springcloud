package com.bdfint.backend.modules.gis.mapper;

import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisSms;

import java.util.List;

import com.bdfint.backend.framework.config.TargetDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GisSmsMapper extends CommonPgMapper<GisSms>{
	
	void insertGisSms(GisSms record);
	
	List<GisSms> queryByParamGisSms(GisSms record);

	int getDaySum(GisSms sms);
	int getSmsReportNum(List list);
}