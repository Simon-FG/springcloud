package com.bdfint.backend.modules.sys.action;

import javax.servlet.http.HttpServletRequest;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardChangeRequest;
import com.bdfint.backend.modules.sys.service.CrmNorthCardChangeRequestService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 北斗卡转网申请action
 * @author 74091
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/cardChangeRequest")
public class CrmNorthCardChangeRequestAction extends BaseAction {
	
	@Autowired
	private CrmNorthCardChangeRequestService crmNorthCardChangeRequestService;
	
	/**
	 * 用户申请方法
	 * @param requestJson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)      
    public String cardChangeRequest(HttpServletRequest  request) throws Exception{     
		return crmNorthCardChangeRequestService.add(request);
    }     
	
	/**
	 * 北斗卡转网列表查询
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/requestList")   
    public PageInfo<CrmNorthCardChangeRequest> list( CrmNorthCardChangeRequest crmNorthCardChangeRequest) throws Exception {
    	Example example = new Example(CrmNorthCardChangeRequest.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(crmNorthCardChangeRequest.getOrderBy())) {
            example.setOrderByClause(crmNorthCardChangeRequest.getOrderBy());
        } else {
            example.setOrderByClause("id DESC");
        }
        if (StringUtils.isNotEmpty(crmNorthCardChangeRequest.getUserId())) {
            criteria.andCondition("user_id"+"=",crmNorthCardChangeRequest.getUserId());
        }
       
        PageInfo<CrmNorthCardChangeRequest> page = crmNorthCardChangeRequestService.getPage(crmNorthCardChangeRequest, example);
        
        return page;
    }
	
}
