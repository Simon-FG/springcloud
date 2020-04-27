package com.bdfint.backend.modules.sys.service;

import java.util.List;

import com.bdfint.backend.framework.common.BaseService;
import com.bdfint.backend.modules.sys.bean.SysMenu;


public interface SysMenuService extends BaseService<SysMenu>{
	
	public List<SysMenu> execute(String target);

}
