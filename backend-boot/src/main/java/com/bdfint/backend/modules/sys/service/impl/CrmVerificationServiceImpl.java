package com.bdfint.backend.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.FilesUtils;
import com.bdfint.backend.modules.sys.bean.CrmVerification;
import com.bdfint.backend.modules.sys.mapper.CrmVerificationMapper;
import com.bdfint.backend.modules.sys.service.CrmVerificationService;

/**
 * 实名认证Service该接口不用
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmVerificationServiceImpl extends BaseIntServiceImpl<CrmVerification> implements CrmVerificationService {
	@Autowired
	private CrmVerificationMapper crmVerificationMapper; 
	

	@Override
	@Transactional
	public String add(HttpServletRequest request){
		
		MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);  
		CrmVerification crmVerification = new CrmVerification();
		String sub = "";
		String folder = "SMRZ";
		try {
			ArrayList<String> pathList = (ArrayList<String>) FilesUtils.fileUpload(request,folder);
			StringBuffer sb=new StringBuffer();
			for(String path:pathList){
				sb.append(path);
			}
			crmVerification.setCompanyName(params.getParameter("companyName"));
			crmVerification.setLegalPerson(params.getParameter("legalPerson"));
			crmVerification.setRegion(params.getParameter("region"));
			crmVerification.setRegisterAddress(params.getParameter("registerAddress"));
			crmVerification.setCreditCode(params.getParameter("creditCode"));
			crmVerification.setLicenseImg(sb.toString());
			crmVerification.setContacts(params.getParameter("contacts"));
			crmVerification.setPhone(params.getParameter("phone"));
			crmVerification.setTelephone(params.getParameter("telephone"));
			crmVerification.setStartTime(new Date());
			crmVerification.setUserId(Integer.valueOf(params.getParameter("userId")));
			super.save(crmVerification);
			sub = "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sub = "0";
		}
		return sub;      
	}
}
