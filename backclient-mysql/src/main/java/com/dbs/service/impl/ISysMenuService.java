package com.dbs.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.dbs.model.SysMenu;

public interface ISysMenuService {
	
	public List<SysMenu> execute(String target);

}
