package com.bdfint.backend.modules.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmSmsRequest;

@Mapper
@Repository
public interface CrmSmsRequestMapper extends CommonMapper<CrmSmsRequest> {
	
}