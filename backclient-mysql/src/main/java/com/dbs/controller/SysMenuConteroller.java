package com.dbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.service.impl.ISysMenuService;


@RequestMapping(value = "/menu")
@RestController
public class SysMenuConteroller {

	@Autowired
	private ISysMenuService sysMenuService;
	
	/***
	 * 菜单查询方法
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/execute")
	public List execute(@RequestParam(value = "target",required=false) String target) {
		List list = sysMenuService.execute(target);
		return list;
	}
}
