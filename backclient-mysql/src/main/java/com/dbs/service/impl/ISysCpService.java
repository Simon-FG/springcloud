package com.dbs.service.impl;

import java.util.List;

import com.dbs.model.SysCp;

public interface ISysCpService {
	
	public List<SysCp> execute(Integer cpId, String cpName);
	
	public List<SysCp>  getCpDetail(String menuId);
	
	public List<SysCp> queryList();
}
