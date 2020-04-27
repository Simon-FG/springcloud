package com.dbs.mapper;

import com.dbs.model.SysJjfaSon;

import java.util.List;

public interface SysJjfaSonMapper {

    List<SysJjfaSon> selectByParentId(Integer parentId);
}