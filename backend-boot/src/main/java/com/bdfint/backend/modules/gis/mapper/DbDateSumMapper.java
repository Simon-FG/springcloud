package com.bdfint.backend.modules.gis.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.gis.bean.DbDateSum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DbDateSumMapper extends CommonMapper<DbDateSum>{

    int addBatch(List<DbDateSum> list);

    List<DbDateSum> getListSend(DbDateSum sum);

    List<DbDateSum> getListReceive(DbDateSum sum);
}