package com.bdfint.backend.modules.operator.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;


public interface OrderOperatorService extends BaseIntService<CrmOrderItem>{

	public Boolean updateStatus(String orderId,String status,String content);

}
