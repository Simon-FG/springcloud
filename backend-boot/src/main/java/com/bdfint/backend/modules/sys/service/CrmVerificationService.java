package com.bdfint.backend.modules.sys.service;

import javax.servlet.http.HttpServletRequest;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmVerification;

public interface CrmVerificationService extends BaseIntService<CrmVerification>{

	public String add(HttpServletRequest request);
}
