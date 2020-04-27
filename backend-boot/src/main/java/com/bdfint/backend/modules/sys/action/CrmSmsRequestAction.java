package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmSmsRequest;
import com.bdfint.backend.modules.sys.service.CrmSmsRequestService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 增值服务购买申请action
 * @author 74091
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/smsRequest")
public class CrmSmsRequestAction extends BaseAction {
	
	@Autowired
	private CrmSmsRequestService crmSmsRequestService;
	
	/**
	 * 用户申请方法
	 * @param requestJson
	 * @return
	 */
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)      
    public String smsRequest(CrmSmsRequest  crmSmsRequest) throws Exception{
		return crmSmsRequestService.add(crmSmsRequest);
    }     
	
	/**
	 * 增值服务申请列表查询
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/requestList")   
    public PageInfo<CrmSmsRequest> list( CrmSmsRequest crmSmsRequest) throws Exception {
    	Example example = new Example(CrmSmsRequest.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(crmSmsRequest.getOrderBy())) {
            example.setOrderByClause(crmSmsRequest.getOrderBy());
        } else {
            example.setOrderByClause("id DESC");
        }
        if (StringUtils.isNotEmpty(crmSmsRequest.getUserId())) {
            criteria.andCondition("user_id"+"=",crmSmsRequest.getUserId());
        }
       
        PageInfo<CrmSmsRequest> page = crmSmsRequestService.getPage(crmSmsRequest, example);
        
        return page;
    }
	
}
