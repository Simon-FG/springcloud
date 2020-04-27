package com.bdfint.backend.modules.operator.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.operator.service.CardBindOperatorService;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;
import com.bdfint.backend.modules.sys.service.CrmAirplaneCardBindService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping(value = "${adminPath}/cardBindOperator")
public class CardBindOperatroAction {
	
	@Autowired
	private CardBindOperatorService cardBindOperatorService;
	
	@Autowired
	private CrmAirplaneCardBindService crmAirplaneCardBindService;
	
	/**
	 * 北斗卡关联审核
	 * @param cardId,status,menuId,parentId
	 * @return
	 */
	@RequestMapping(value = "/reviewBind", method = RequestMethod.POST)
//	@RequiresPermissions("sys:cardBindOperator:reviewBind")
	public Boolean reviewOrder(@RequestParam(value = "cardId") String cardId,
							   @RequestParam(value = "status") String status,
							   @RequestParam(value = "content", required = false) String content,
							   @RequestParam(value = "menuId",required=false) String menuId,
							   @RequestParam(value = "parentId",required=false) Integer parentId){
		return cardBindOperatorService.updateStatus(cardId,status, content,menuId,parentId);
	}
	
	/**
	 * 北斗卡关联绑定
	 * @param crmAirplaneCardBind
	 * @return
	 */
	@RequestMapping(value = "/cardBind", method = RequestMethod.POST)
	public Boolean cardBind(CrmAirplaneCardBind crmAirplaneCardBind) throws Exception{
		return cardBindOperatorService.add(crmAirplaneCardBind);
		
	}

	/**
	 * 北斗卡关联列表
	 * @param crmAirplaneCardBind
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/requestList")
//    public PageInfo<CrmAirplaneCardBind> list( CrmAirplaneCardBind crmAirplaneCardBind) throws Exception {
//    	Example example = new Example(CrmAirplaneCardBind.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (StringUtils.isNotEmpty(crmAirplaneCardBind.getOrderBy())) {
//            example.setOrderByClause(crmAirplaneCardBind.getOrderBy());
//        } else {
//            example.setOrderByClause("id DESC");
//        }
//        if (StringUtils.isNotEmpty(crmAirplaneCardBind.getStatus())) {
//            criteria.andCondition("status"+"=",crmAirplaneCardBind.getStatus());
//        }
//        if (StringUtils.isNotEmpty(crmAirplaneCardBind.getCardId())) {
//            criteria.andCondition("card_id"+"=",crmAirplaneCardBind.getCardId());
//        }
//        if (StringUtils.isNotEmpty(crmAirplaneCardBind.getAirplaneId())) {
//            criteria.andCondition("airplane_id"+"=",crmAirplaneCardBind.getAirplaneId());
//        }
//        if (StringUtils.isNotEmpty(crmAirplaneCardBind.getUserId())) {
//            criteria.andCondition("user_id"+"=",crmAirplaneCardBind.getUserId());
//        }
//        PageInfo<CrmAirplaneCardBind> page = crmAirplaneCardBindService.getPage(crmAirplaneCardBind, example);
//
//        return page;
//    }

}
