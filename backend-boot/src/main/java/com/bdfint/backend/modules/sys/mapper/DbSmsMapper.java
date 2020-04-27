package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.DbSms;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DbSmsMapper extends CommonMapper<DbSms>{
}