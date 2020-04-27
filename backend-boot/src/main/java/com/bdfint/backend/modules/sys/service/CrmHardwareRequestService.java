package com.bdfint.backend.modules.sys.service;


import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmHardwareRequest;

public interface CrmHardwareRequestService extends BaseIntService<CrmHardwareRequest> {

	public String add(CrmHardwareRequest crmHardwareRequest) throws Exception;
	
}
