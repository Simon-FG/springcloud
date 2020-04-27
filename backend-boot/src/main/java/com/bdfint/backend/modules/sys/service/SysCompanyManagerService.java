package com.bdfint.backend.modules.sys.service;

import java.util.List;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.SysCompanyManager;


public interface SysCompanyManagerService extends BaseIntService<SysCompanyManager>{
	
	public List<SysCompanyManager> queryLikeCompany(String company);
	
	public String queryCompanyName(String userId);

}
