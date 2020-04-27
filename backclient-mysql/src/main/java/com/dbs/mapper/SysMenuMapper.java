package com.dbs.mapper;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.dbs.model.SysMenu;

public interface SysMenuMapper {
    
    public  List<SysMenu> queryMenuByPage(Map queryMap);

}