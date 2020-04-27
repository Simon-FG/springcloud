package com.dbs.mapper;

import com.dbs.model.SysNews;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysNewsMapper {

    SysNews selectByPrimaryKey(Integer newsId);

    List<SysNews> selectByStatus(String status);

    List<SysNews> selectAll();
}