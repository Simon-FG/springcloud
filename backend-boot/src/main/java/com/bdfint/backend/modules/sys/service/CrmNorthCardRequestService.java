package com.bdfint.backend.modules.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardRequest;

public interface CrmNorthCardRequestService extends BaseIntService<CrmNorthCardRequest> {

	public String add(CrmNorthCardRequest crmNorthCardRequest,HttpServletRequest request);
	
	public List<CrmNorthCardRequest>  getDetail(String requestId);

	public CrmNorthCardRequest getByRequestId(String requestId);
	
}
