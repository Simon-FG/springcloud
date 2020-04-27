package com.bdfint.backend.modules.sys.action;

import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.sys.service.SysMenuService;

@RestController
@RequestMapping(value = "${adminPath}/menu")
public class SysMenuAction extends BaseAction {

	@Autowired
	private SysMenuService sysMenuService;

	/***
	 * 菜单查询方法
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/execute")
	public List execute(@RequestParam(value = "target",required=false) String target) {
		return sysMenuService.execute(target);
	}
}