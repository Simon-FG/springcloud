package com.bdfint.backend.modules.sys.service.impl;

import java.util.Date;

import com.bdfint.backend.modules.sys.bean.CrmSmsRequest;
import com.bdfint.backend.modules.sys.mapper.CrmSmsRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.NorthCardUtils;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.bean.CrmOrderOperate;
import com.bdfint.backend.modules.sys.mapper.CrmOrderItemMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderOperateMapper;
import com.bdfint.backend.modules.sys.service.CrmSmsRequestService;
import com.bdfint.backend.modules.sys.utils.UserUtils;

/**
 * 增值服务申请Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmSmsRequestServiceImpl extends BaseIntServiceImpl<CrmSmsRequest> implements CrmSmsRequestService {
	@Autowired
	private CrmSmsRequestMapper crmSmsRequestMapper;
	
	@Autowired
	private CrmOrderItemMapper crmOrderItemMapper;
	
	@Autowired
	private CrmOrderOperateMapper crmOrderOperateMapper;
	
	/**
	 * 订单申请方法
	 */
	@Override
	@Transactional
	public String add(CrmSmsRequest crmSmsRequest){
		CrmOrderItem crmOrderItem = new CrmOrderItem();
		CrmOrderOperate crmOrderOperate = new CrmOrderOperate();
		String sub = "";
		String requestId = NorthCardUtils.getRequestCardNumber(UserUtils.ORDER_TYPE_SMS);
		try {
			crmSmsRequest.setRequestId(requestId);
			crmSmsRequest.setStartTime(new Date());
			crmSmsRequest.setUserId(UserUtils.getUser().getId());
			super.save(crmSmsRequest);
			crmOrderItem.setOrderId(requestId);
			crmOrderItem.setCreateTime(new Date());
			crmOrderItem.setFullPrice(crmSmsRequest.getPrice());//需要前台获取单价
			crmOrderItem.setUserName(UserUtils.getUser().getName());
			crmOrderItem.setGoodsId(2);//前台传产品ID
			crmOrderItem.setMenuId("9");//前台传menuId
//			crmOrderItem.setGoodsId(Integer.parseInt(params.getParameter("goodsId")));
//			crmOrderItem.setMenuId(params.getParameter("menuId"));
			crmOrderItem.setOrderType(UserUtils.ORDER_TYPE_SMS);
			crmOrderItem.setStatus("1");
			crmOrderItem.setStartTime(new Date());
			crmOrderItem.setUserId(UserUtils.getUser().getId());
			crmOrderItemMapper.add(crmOrderItem);
			crmOrderOperate.setOrderId(crmOrderItem.getOrderId());
			crmOrderOperate.setOperate("订单已提交");
			crmOrderOperate.setOperateBy(UserUtils.getUser().getId());
			crmOrderOperateMapper.insert(crmOrderOperate);
			sub = requestId;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sub;
	}

}