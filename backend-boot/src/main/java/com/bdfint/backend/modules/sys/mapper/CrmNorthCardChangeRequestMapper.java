package com.bdfint.backend.modules.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardChangeRequest;

@Mapper
@Repository
public interface CrmNorthCardChangeRequestMapper extends CommonMapper<CrmNorthCardChangeRequest>{
   
}