package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmMobileManager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CrmMobileManagerMapper extends CommonMapper<CrmMobileManager> {
}