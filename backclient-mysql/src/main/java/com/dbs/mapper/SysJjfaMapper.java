package com.dbs.mapper;

import com.dbs.model.SysJjfa;

import java.util.List;

public interface SysJjfaMapper {

    SysJjfa selectByPrimaryKey(Integer faId);

    List<SysJjfa> selectOneWithSonByMenuId(String menuId);

    List<SysJjfa> selectListInHome();

}