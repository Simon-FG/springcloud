package com.bdfint.backend.modules.gis.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.framework.common.CommonPgMapper;
import com.bdfint.backend.modules.gis.bean.GisEnrouteManager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GisEnrouteManagerMapper extends CommonPgMapper<GisEnrouteManager>{
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(GisEnrouteManager record);
//
//    int insertSelective(GisEnrouteManager record);
//
//    GisEnrouteManager selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(GisEnrouteManager record);
//
//    int updateByPrimaryKey(GisEnrouteManager record);

    List<GisEnrouteManager> queryByParamGisEnroute(GisEnrouteManager enrouteManager);
}