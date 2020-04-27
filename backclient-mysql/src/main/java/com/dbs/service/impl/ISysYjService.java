package com.dbs.service.impl;

import java.util.List;
import com.dbs.model.SysYj;

public interface ISysYjService {
	
	public List<SysYj> execute(Integer yjId, String yjName);
	
	public List<SysYj> getYjDetail(Integer yjId);
	
}
