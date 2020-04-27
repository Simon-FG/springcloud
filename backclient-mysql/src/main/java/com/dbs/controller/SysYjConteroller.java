package com.dbs.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.model.SysYj;
import com.dbs.service.impl.ISysYjService;


@RequestMapping(value = "/yjsb")
@RestController
public class SysYjConteroller {
	
	@Autowired
	private ISysYjService sysYjService;
	
	/***
     * 查看硬件详细信息
     * @param cpId
     * @return
     */
    @RequestMapping(value = "/getCpDetail")
    public List<SysYj> getCpDetail(@RequestParam(value="yjId") Integer yjId){
        return sysYjService.getYjDetail(yjId);
    }
    
	/***
	 * 硬件查询方法
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/execute")
	public List execute(@RequestParam(value = "yjId",required=false) Integer yjId,@RequestParam(value = "yjName",required=false) String yjName) {
		List list = sysYjService.execute(yjId,yjName);
		return list;
	}

}
