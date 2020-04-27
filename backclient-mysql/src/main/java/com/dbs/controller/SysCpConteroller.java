package com.dbs.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.model.SysCp;
import com.dbs.service.impl.ISysCpService;


@RequestMapping(value = "/cpfw")
@RestController
public class SysCpConteroller {

	@Autowired
	private ISysCpService sysCpService;
	
	/***
     * 点击菜单查看详细信息
     * @param cpId
     * @return
     */
    @RequestMapping(value = "/getCpDetail")
    public List<SysCp>  getCpDetail(@RequestParam(value="menuId") String menuId){
        return sysCpService.getCpDetail(menuId);
    }
	
	/***
	 * 菜单查询方法
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/execute")
	public List execute(@RequestParam(value = "cpId",required=false) Integer cpId,@RequestParam(value = "cpName",required=false) String cpName) {
		List list = sysCpService.execute(cpId,cpName);
		return list;
	}
	
	/***
	 * 首页显示
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/showHome")
	public List showHome() {
		return sysCpService.queryList();
	}

}
