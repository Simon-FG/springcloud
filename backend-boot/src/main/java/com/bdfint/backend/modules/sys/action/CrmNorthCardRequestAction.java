package com.bdfint.backend.modules.sys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardRequest;
import com.bdfint.backend.modules.sys.service.CrmNorthCardRequestService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 北斗卡申请action
 * @author 74091
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/cardRequest")
public class CrmNorthCardRequestAction extends BaseAction {
	
	@Autowired
	private CrmNorthCardRequestService crmNorthCardRequestService;
	
	/**
	 * 用户申请方法
	 * @param requestJson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)      
    public String cardRequest(CrmNorthCardRequest crmNorthCardRequest,HttpServletRequest  request) throws Exception{     
		return crmNorthCardRequestService.add(crmNorthCardRequest,request);
    }     
	
	/**
	 * 北斗卡转网申请列表查询
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/requestList")   
    public PageInfo<CrmNorthCardRequest> list( CrmNorthCardRequest crmNorthCardRequest) throws Exception {
    	Example example = new Example(CrmNorthCardRequest.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(crmNorthCardRequest.getOrderBy())) {
            example.setOrderByClause(crmNorthCardRequest.getOrderBy());
        } else {
            example.setOrderByClause("id DESC");
        }
        if (StringUtils.isNotEmpty(crmNorthCardRequest.getUserId())) {
            criteria.andCondition("user_id"+"=",crmNorthCardRequest.getUserId());
        }
       
        PageInfo<CrmNorthCardRequest> page = crmNorthCardRequestService.getPage(crmNorthCardRequest, example);
        
        return page;
    }
	
	/**
	 * 获取北斗卡申请详细信息
	 * @param requestId
	 * @return
	 */
	@RequestMapping(value = "/getDetail")  
	public List<CrmNorthCardRequest> getDetail(@RequestParam String requestId){
		
		return crmNorthCardRequestService.getDetail(requestId);
	}
}
