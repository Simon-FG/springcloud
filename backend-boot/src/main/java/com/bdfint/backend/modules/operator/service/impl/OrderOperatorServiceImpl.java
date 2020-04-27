package com.bdfint.backend.modules.operator.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.operator.service.OrderOperatorService;
import com.bdfint.backend.modules.sys.bean.CrmHardwareRequest;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardChangeRequest;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardRequest;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.bean.CrmOrderOperate;
import com.bdfint.backend.modules.sys.bean.CrmPrivateMessage;
import com.bdfint.backend.modules.sys.bean.CrmSmsRequest;
import com.bdfint.backend.modules.sys.mapper.CrmHardwareRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardChangeRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderItemMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderOperateMapper;
import com.bdfint.backend.modules.sys.mapper.CrmPrivateMessageMapper;
import com.bdfint.backend.modules.sys.mapper.CrmSmsRequestMapper;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * 订单Service
 * @author 74091
 *
 */
@Service
@Transactional
public class OrderOperatorServiceImpl extends BaseIntServiceImpl<CrmOrderItem> implements OrderOperatorService {

	@Autowired
	private CrmOrderItemMapper crmOrderItemMapper; 
	
	@Autowired
	private CrmOrderOperateMapper crmOrderOperateMapper;
	
	@Autowired
	private CrmPrivateMessageMapper crmPrivateMessageMapper;
	
	@Autowired
	private CrmNorthCardRequestMapper crmNorthCardRequestMapper;//北斗卡申请mapper
	
	@Autowired
	private CrmHardwareRequestMapper crmHardwareRequestMapper;//硬件申请mapper
	
	@Autowired
	private CrmSmsRequestMapper crmSmsRequestMapper;//增值服务mapper
	
	@Autowired
	private CrmNorthCardChangeRequestMapper crmNorthCardChangeRequestMapper;//转网mapper
	
	
	/**
	 * 管理员审核修改订单状态
	 */
	@Override
	public Boolean updateStatus(String orderId,String status,String content) {
		CrmOrderOperate crmOrderOperate = new CrmOrderOperate();
		CrmPrivateMessage crmPrivateMessage = new CrmPrivateMessage();
        String msg = "";
		String type = orderId.replaceAll("[^a-zA-Z].*$", "");
		try {
			CrmOrderItem crmOrderItem = new CrmOrderItem();
			crmOrderItem.setOrderId(orderId);
			Example exampleOrder = new Example(CrmOrderItem.class);
			exampleOrder.createCriteria().andEqualTo("orderId", orderId);
			List<CrmOrderItem> selectByExampleOrder = crmOrderItemMapper.selectByExample(exampleOrder);
			if(selectByExampleOrder.size()==1)
			{
				String str = UserUtils.updateDataToDatabase(CrmOrderItem.class, crmOrderItemMapper, orderId, UserUtils.ORDER_STATUS, status,UserUtils.ORDER_KEY );
				if(str != null && !str.equals("")){
					crmOrderOperate.setOrderId(orderId);
					if(status.equals(UserUtils.STATUS_VALb)){
						crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_PASS);
					}else if(status.equals(UserUtils.STATUS_VALc)){
						crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_FINISH);
					}else{
						crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_OUT + "("+content+")");
					}
					crmOrderOperate.setOperateBy(UserUtils.getUser().getId());
					crmOrderOperateMapper.insert(crmOrderOperate);
					switch (type) {
					case "SB":
                        msg = UserUtils.MSG_TARGET_HARDWARE;
						UserUtils.updateDataToDatabase(CrmHardwareRequest.class, crmHardwareRequestMapper, orderId, UserUtils.ORDER_STATUS, status,UserUtils.REQUEST_KEY);
						break;
					case "KSQ":
                        msg = UserUtils.MSG_TARGET_CARD;
						UserUtils.updateDataToDatabase(CrmNorthCardRequest.class, crmNorthCardRequestMapper, orderId, UserUtils.ORDER_STATUS, status,UserUtils.REQUEST_KEY);
						break;
					case "ZZ":
                        msg = UserUtils.MSG_TARGET_ZZ;
						UserUtils.updateDataToDatabase(CrmSmsRequest.class, crmSmsRequestMapper, orderId, UserUtils.ORDER_STATUS, status,UserUtils.REQUEST_KEY);
						break;
					case "ZW":
                        msg = UserUtils.MSG_TARGET_CARD_ZW;
						UserUtils.updateDataToDatabase(CrmNorthCardChangeRequest.class, crmNorthCardChangeRequestMapper, orderId, UserUtils.ORDER_STATUS, status,UserUtils.REQUEST_KEY);
						break;
//					case "QT":
//					UserUtils.updateDataToDatabase(CrmNorthCardRequest.class, crmNorthCardRequestMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.REQUEST_KEY);
//						break;
					default:
						break;
					}
				}
                CrmOrderItem item = selectByExampleOrder.get(0);
                UserUtils.createPrivateMessage(msg,status,item.getUserId(),content,item.getMenuId(),item.getGoodsId());
				return true;
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

}