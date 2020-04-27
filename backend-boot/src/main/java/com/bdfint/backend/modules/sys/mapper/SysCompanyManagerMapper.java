package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.SysCompanyManager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysCompanyManagerMapper extends CommonMapper<SysCompanyManager> {

//    int add(SysCompanyManager companyManager);
}