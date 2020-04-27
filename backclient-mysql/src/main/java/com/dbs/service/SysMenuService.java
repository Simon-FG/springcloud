package com.dbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.dbs.mapper.SysMenuMapper;
import com.dbs.model.SysMenu;
import com.dbs.service.impl.ISysMenuService;
@Service
@Transactional
public class SysMenuService implements ISysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper; 
	
	/***
	 * 菜单树查询方法
	 * @return
	 */
	public List<SysMenu> execute(String target) {
		//retSysMenu= new SysMenu();
		Map queryMap = new HashMap();
		if(target != null && !target.equals("")){
			queryMap.put("target", target);
		}
		List<SysMenu> menuList = sysMenuMapper.queryMenuByPage(queryMap); 
	    // 最后的结果
	    List<SysMenu> newMenuList = new ArrayList<SysMenu>();
	    // 先找到所有的一级菜单
	    SysMenu sysMenu = null;
	    
	    for (int i = 0; i < menuList.size(); i++) {
	        // 一级菜单没有parentId
	        if (menuList.get(i).getParentId().equals("1")) {
	        	sysMenu= menuList.get(i);
				break;
	        }
	    }
	    if(sysMenu!=null){
	    	getChild(sysMenu,menuList);
	    }
		return sysMenu.getChildMenus();

	}

	/**
	 * 递归查找子菜单
	 * @return
	 */
	private void getChild(SysMenu sysMenu, List<SysMenu> menuList) {
	    // 子菜单
	    List<SysMenu> childList = new ArrayList<>();
	    for (SysMenu menu : menuList) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	            if (menu.getParentId().equals(sysMenu.getId())) {
	                childList.add(menu);
	            }
	    }
	    if (childList.size() != 0) {
	    	sysMenu.setChildMenus(childList);
	    	for(SysMenu menu:childList)
	    	{
	    		getChild(menu,menuList);
	    	}
	    }
	}
}
