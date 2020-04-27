package com.dbs.mapper;

import com.dbs.model.SysYjSon;

public interface SysYjSonMapper {
    int deleteByPrimaryKey(Integer yjgnId);

    int insert(SysYjSon record);

    int insertSelective(SysYjSon record);

    SysYjSon selectByPrimaryKey(Integer yjgnId);

    int updateByPrimaryKeySelective(SysYjSon record);

    int updateByPrimaryKey(SysYjSon record);
}