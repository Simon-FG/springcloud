package com.bdfint.backend.modules.sys.service;

import java.util.List;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;

public interface CrmOrderItemService extends BaseIntService<CrmOrderItem>{

	public String addAddress(CrmOrderItem crmOrderItem);
	
//	public List<CrmOrderItem> execute(CrmOrderItem crmOrderItem);
	
	public Boolean deleteFlag(String orderId);
	
	public List<CrmOrderItem> orderDetail(String orderId,Integer goodsId,Integer addressId,String orderType);

	public Boolean addSmsOrder(String orderId, String smsOrder);

	//订单受理
	public Boolean orderHandle(String orderId,String handleStatus,String userId);

}
