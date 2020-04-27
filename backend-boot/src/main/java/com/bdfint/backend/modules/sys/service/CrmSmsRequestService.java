package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmSmsRequest;

public interface CrmSmsRequestService extends BaseIntService<CrmSmsRequest> {

	public String add(CrmSmsRequest crmSmsRequest);
	
}
