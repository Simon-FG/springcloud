package com.dbs.mapper;

import javax.servlet.http.HttpServletRequest;

import com.dbs.model.SysCpSon;

public interface SysCpSonMapper {
    int deleteByPrimaryKey(Integer cpgnId);

    int insert(SysCpSon record);

    int insertSelective(SysCpSon record);

    SysCpSon selectByPrimaryKey(Integer cpgnId);

    int updateByPrimaryKeySelective(SysCpSon record);

    int updateByPrimaryKey(SysCpSon record);
    
    public void add(HttpServletRequest request); 
}