package com.bdfint.backend.modules.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmOrderOperate;

@Mapper
@Repository
public interface CrmOrderOperateMapper extends CommonMapper<CrmOrderOperate>{
	
	int insert(CrmOrderOperate crmOrderOperate);
}