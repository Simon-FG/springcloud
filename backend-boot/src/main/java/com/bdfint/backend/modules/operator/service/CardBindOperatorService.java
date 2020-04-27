package com.bdfint.backend.modules.operator.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;
import org.springframework.transaction.annotation.Transactional;


public interface CardBindOperatorService extends BaseIntService<CrmAirplaneCardBind>{
	
	public Boolean add(CrmAirplaneCardBind crmAirplaneCardBind);

	Boolean updateStatus(String cardId, String status, String content, String menuId, Integer parentId);
}
