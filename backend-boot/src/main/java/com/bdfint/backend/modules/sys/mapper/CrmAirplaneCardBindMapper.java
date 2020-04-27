package com.bdfint.backend.modules.sys.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;

import java.util.List;

@Mapper
@Repository
public interface CrmAirplaneCardBindMapper extends CommonMapper<CrmAirplaneCardBind>{
	
//	public List<CrmAirplaneCardBind> queryCardAndAirplane(@Param("userId") String userId);
	
	CrmAirplaneCardBind queryByCardIdAirplaneCardBind(CrmAirplaneCardBind record);

	List<CrmAirplaneCardBind> getListBy(CrmAirplaneCardBind bind);
	
	List<CrmAirplaneCardBind> queryBindList ();

	List<CrmAirplaneCardBind> getReqList(CrmAirplaneCardBind bind);
}