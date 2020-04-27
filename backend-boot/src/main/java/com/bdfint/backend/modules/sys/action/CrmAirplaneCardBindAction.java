package com.bdfint.backend.modules.sys.action;

import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;
import com.bdfint.backend.modules.sys.service.CrmAirplaneCardBindService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 北斗卡关联申请action
 * @author 74091
 *
 *
 *  -------接口-------
 *  北斗卡关联列表
 *  http://localhost:8082/admin/cardBind/bindList(cardId、userId、airplaneId、status、bound、name、
 *          orderBy(crm_north_card a、crm_airplane_card_bind b) eg:a.card_id asc b.airplane_id desc)
 *  查询需要绑定的机尾号是否被占用("1"航空器属于别人名下  "2"航空器已绑定在其他北斗卡下  null:可正常绑定)
 *  http://localhost:8082/admin/cardBind//queryAirplaneOccupy
 *  绑定申请方法（airplaneId、cardId）
 *  http://localhost:8082/admin/cardBind/userAdd
 */
@RestController
@RequestMapping(value = "${adminPath}/cardBind")
public class CrmAirplaneCardBindAction extends BaseAction {
	
	@Autowired
	private CrmAirplaneCardBindService crmAirplaneCardBindService;
	
	/**
	 * 绑定申请方法
	 * @param crmAirplaneCardBind
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)      
    public Boolean cardRequest(CrmAirplaneCardBind crmAirplaneCardBind) throws Exception{     
		return crmAirplaneCardBindService.add(crmAirplaneCardBind);
    }     
	
	/**
	 * 北斗卡关联审核列表查询
	 * @param crmAirplaneCardBind
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/requestList")   
    public PageInfo<CrmAirplaneCardBind> list( CrmAirplaneCardBind crmAirplaneCardBind) throws Exception {
    	return crmAirplaneCardBindService.getReqList(crmAirplaneCardBind);
    }

	/**
	 * 北斗卡关联列表查询
	 */
	@RequestMapping(value = "/bindList")
    public PageInfo<CrmAirplaneCardBind> bindList( CrmAirplaneCardBind crmAirplaneCardBind) throws Exception {
    	return crmAirplaneCardBindService.getPageCardLeft(crmAirplaneCardBind);
    }
	
	/**
	 * 用户通过提交北斗卡号变更与之关联的机尾号
	 * @param cardId
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/changeBind")   
	public String change(@RequestParam(value = "cardId",required=false) String cardId,@RequestParam(value = "note",required=false) String note){
		
		return crmAirplaneCardBindService.changeBind(cardId,note);
	}
	
	/**
	 * 通过用户ID获取该未绑定的卡号与机尾号
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getCardAndAirplane")
	public List getCardAndAirplane(@RequestParam(value = "userId",required=false) String userId){
		
		return crmAirplaneCardBindService.getCardAndAirplane(userId);
	}
	
	/**
	 * 管理员未审核前用户可取消绑定申请
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value = "/deleteBind")
	public Boolean deleteBind(@RequestParam(value = "cardId",required=false) String cardId){
		
		return crmAirplaneCardBindService.deleteBind(cardId);
	}
	
	/**
	 * <p>Title: queryByCardIdAirplaneCardBind</p>  
	 * <p>Description: 根据北斗卡cardId查询飞行器信息 </p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryByCardIdAirplaneCardBind",method=RequestMethod.GET)
	public CrmAirplaneCardBind queryByCardIdAirplaneCardBind(CrmAirplaneCardBind record) throws Exception {
		return crmAirplaneCardBindService.queryByCardIdAirplaneCardBind(record);
	}

	/**
	 * 查询需要绑定的机尾号是否被占用
	 * @param airplaneId
	 * @return ("1"航空器属于别人名下  "2"航空器已绑定在其他北斗卡下  null:可正常绑定)
	 */
	@RequestMapping(value = "/queryAirplaneOccupy")
	public String queryAirplaneOccupy(@RequestParam(value = "airplaneId",required=false) String airplaneId){
		return crmAirplaneCardBindService.queryAirplaneOccupy(airplaneId);
	}
	
//	/**
//	 * 获取已绑定的北斗卡与机位号
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/queryBindList")
//	public String queryBindList()throws Exception{
//		return crmAirplaneCardBindService.queryBindList();
//	}
	
}
