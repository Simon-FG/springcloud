package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.BaseIntEntity;
import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.DbWarning;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DbWarningMapper extends CommonMapper<DbWarning> {
}