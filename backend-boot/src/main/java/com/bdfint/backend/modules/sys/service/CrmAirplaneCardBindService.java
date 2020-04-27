package com.bdfint.backend.modules.sys.service;

import java.util.List;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;
import com.github.pagehelper.PageInfo;

public interface CrmAirplaneCardBindService extends BaseIntService<CrmAirplaneCardBind> {

	public Boolean add(CrmAirplaneCardBind crmAirplaneCardBind);

	//设置北斗卡绑定状态
	boolean changeCardBound(String cardId, String bound);

	//设置飞行器绑定状态
	boolean changeAirplaneBound(String airplaneId, String bound);

	public String changeBind(String cardId, String note);
	
	public List getCardAndAirplane(String userId);
	
	public Boolean deleteBind(String cardId);
	
	/**
	 * <p>Title: queryByCardIdAirplaneCardBind</p>  
	 * <p>Description: 根据北斗卡cardId查询飞行器信息 </p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public CrmAirplaneCardBind queryByCardIdAirplaneCardBind(CrmAirplaneCardBind record)throws Exception;

	public PageInfo<CrmAirplaneCardBind> getReqList(CrmAirplaneCardBind crmAirplaneCardBind) throws Exception;

	PageInfo<CrmAirplaneCardBind> getPageCardLeft(CrmAirplaneCardBind bind);

	/**
	 * 查询机尾号是否被占用
	 * @param airplaneId
	 * @return
	 */
	public String queryAirplaneOccupy(String airplaneId);
	
	/**
	 * 获取已绑定的北斗卡与机尾号
	 */
	public String queryBindList()throws Exception;
	
}
