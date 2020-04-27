package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmHardwareRequest;
import com.bdfint.backend.modules.sys.service.CrmHardwareRequestService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 硬件设备购买申请action
 * @author 74091
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/hardwareRequest")
public class CrmHardwareRequestAction extends BaseAction {
	
	@Autowired
	private CrmHardwareRequestService crmHardwareRequestService;
	
	/**
	 * 用户申请方法
	 * @param requestJson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)      
    public String hardwareRequest(CrmHardwareRequest  crmHardwareRequest) throws Exception{     
		return crmHardwareRequestService.add(crmHardwareRequest);
    }     
	
	/**
	 * 硬件购买申请列表查询
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/requestList")   
    public PageInfo<CrmHardwareRequest> list( CrmHardwareRequest crmHardwareRequest) throws Exception {
    	Example example = new Example(CrmHardwareRequest.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(crmHardwareRequest.getOrderBy())) {
            example.setOrderByClause(crmHardwareRequest.getOrderBy());
        } else {
            example.setOrderByClause("id DESC");
        }
        if (StringUtils.isNotEmpty(crmHardwareRequest.getUserId())) {
            criteria.andCondition("user_id"+"=",crmHardwareRequest.getUserId());
        }
       
        PageInfo<CrmHardwareRequest> page = crmHardwareRequestService.getPage(crmHardwareRequest, example);
        
        return page;
    }
	
}
