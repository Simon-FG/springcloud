package com.bdfint.backend.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.FilesUtils;
import com.bdfint.backend.framework.util.NorthCardUtils;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardChangeRequest;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.bean.CrmOrderOperate;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardChangeRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderItemMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderOperateMapper;
import com.bdfint.backend.modules.sys.service.CrmNorthCardChangeRequestService;
import com.bdfint.backend.modules.sys.utils.UserUtils;

/**
 * 北斗卡转网申请Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmNorthCardChangeRequestServiceImpl extends BaseIntServiceImpl<CrmNorthCardChangeRequest> implements CrmNorthCardChangeRequestService {
	@Autowired
	private CrmNorthCardChangeRequestMapper crmNorthCardChangeRequestMapper;
	
	@Autowired
	private CrmOrderItemMapper crmOrderItemMapper;
	
	@Autowired
	private CrmOrderOperateMapper crmOrderOperateMapper;
	
	/**
	 * 北斗卡转网订单申请方法
	 */
	@Override
	@Transactional
	public String add(HttpServletRequest request){
		MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);  
		CrmNorthCardChangeRequest crmNorthCardChangeRequest = new CrmNorthCardChangeRequest();
		
		CrmOrderItem crmOrderItem = new CrmOrderItem();
		CrmOrderOperate crmOrderOperate = new CrmOrderOperate();
		String sub = "";
		String folder = "BDKZW";
		String requestId = NorthCardUtils.getRequestCardNumber(UserUtils.ORDER_TYPE_SMSCHANGE);
		try {
			List pathList = FilesUtils.fileUpload(request,folder);
			crmNorthCardChangeRequest.setRequestId(requestId);
			crmNorthCardChangeRequest.setCardId(params.getParameter("cardId"));
			crmNorthCardChangeRequest.setReason(params.getParameter("reason"));
			crmNorthCardChangeRequest.setPrjName(params.getParameter("prjName"));
			crmNorthCardChangeRequest.setCompany(params.getParameter("company"));
			crmNorthCardChangeRequest.setFileUrl(pathList.get(0).toString());
			crmNorthCardChangeRequest.setStartTime(new Date());
			crmNorthCardChangeRequest.setUserId(UserUtils.getUser().getId());
			super.save(crmNorthCardChangeRequest);
			crmOrderItem.setOrderId(requestId);
			crmOrderItem.setCreateTime(new Date());
			crmOrderItem.setFullPrice(crmNorthCardChangeRequest.getPrice());//需要前台获取单价
			crmOrderItem.setUserName(UserUtils.getUser().getName());
			crmOrderItem.setGoodsId(2);//前台传产品ID
			crmOrderItem.setMenuId("9");//前台传menuId
			
			crmOrderItem.setOrderType(UserUtils.ORDER_TYPE_SMSCHANGE);
			crmOrderItem.setStatus("1");
			crmOrderItem.setStartTime(new Date());
			crmOrderItem.setUserId(UserUtils.getUser().getId());
			crmOrderItemMapper.add(crmOrderItem);
			crmOrderOperate.setOrderId(crmOrderItem.getOrderId());
			crmOrderOperate.setOperate("订单已提交");//获取字典表中状态类型
			crmOrderOperate.setOperateBy(UserUtils.getUser().getId());
			crmOrderOperateMapper.insert(crmOrderOperate);
			sub = requestId;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sub = "0";
		}
		return sub;
	}
}