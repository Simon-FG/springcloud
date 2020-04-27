package com.bdfint.backend.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseServiceImpl;
import com.bdfint.backend.modules.sys.bean.SysMenu;
import com.bdfint.backend.modules.sys.mapper.SysMenuMapper;
import com.bdfint.backend.modules.sys.service.SysMenuService;

import org.apache.commons.lang.StringUtils;

@Service
@Transactional 
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper; 

	@Transient
	private List<SysMenu> menuList;
	
	/***
	 * 菜单树查询方法
	 * @return
	 */
	@Override
	public List<SysMenu> execute(String target) {
		Map queryMap = new HashMap();
		try {
			if(target != null && !target.equals("")){
				queryMap.put("target", target);
			}
			
			menuList = sysMenuMapper.queryMenuByPage(queryMap); 
			// 查看结果
		    for (SysMenu menu : menuList) {
		        System.out.println(menu);
		    }
		    // 最后的结果
		    List<SysMenu> newMenuList = new ArrayList<SysMenu>();
		    // 先找到所有的一级菜单
		    SysMenu sysMenu =null;
		    for (int i = 0; i < menuList.size(); i++) {
		        // 一级菜单没有parentId
		        if (menuList.get(i).getParentId().equals("1")) {
		        	sysMenu= menuList.get(i);
					break;
		        }
		    }
		    if(sysMenu!=null)
		    {
		    	for (int i = 0; i < menuList.size(); i++) {
			        // 一级菜单没有parentId
			        if (menuList.get(i).getParentId().equals(sysMenu.getId())) {
						newMenuList.add(menuList.get(i));
			        }
			    }
		    }
		    
		    // 为一级菜单设置子菜单，getChild是递归调用的
		    for (SysMenu menu : newMenuList) {
		        menu.setChildMenus(getChild(menu.getId(), menuList));
		    }
		    Map<String,Object> jsonMap = new HashMap<>();
		    jsonMap.put("menu", newMenuList);
		    //System.out.println(gson.toJson(jsonMap));
			
			return newMenuList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 递归查找子菜单
	 * @return
	 */
	private List<SysMenu> getChild(String id, List<SysMenu> menuList) {
	    // 子菜单
	    List<SysMenu> childList = new ArrayList<>();
	    for (SysMenu menu : menuList) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (!menu.getParentId().equals("0")) {
	            if (menu.getParentId().equals(id)) {
	                childList.add(menu);
	            }
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (SysMenu menu : childList) {// 没有url子菜单还有子菜单
	        if (StringUtils.isBlank(menu.getHref())) {
	            menu.setChildMenus(getChild(menu.getId(), menuList));
	        }
	    } 
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

}
