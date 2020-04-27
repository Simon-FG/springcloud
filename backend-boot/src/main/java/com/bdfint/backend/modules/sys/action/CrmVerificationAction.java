package com.bdfint.backend.modules.sys.action;

import javax.servlet.http.HttpServletRequest;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.sys.service.CrmVerificationService;

@RestController
@RequestMapping(value = "${adminPath}/verification")
public class CrmVerificationAction extends BaseAction {

	@Autowired
	private CrmVerificationService crmVerificationService;
	
	/***
	 * 用户申请方法
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)      
    public String handleFileUpload(HttpServletRequest request) {      
		return crmVerificationService.add(request);
    }     

}
