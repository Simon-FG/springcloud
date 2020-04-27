package com.bdfint.backend.modules.sys.service;

import javax.servlet.http.HttpServletRequest;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardChangeRequest;

public interface CrmNorthCardChangeRequestService extends BaseIntService<CrmNorthCardChangeRequest> {

	public String add(HttpServletRequest request);
	
}
