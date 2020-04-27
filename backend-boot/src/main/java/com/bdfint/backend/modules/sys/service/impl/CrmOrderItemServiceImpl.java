package com.bdfint.backend.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.Collections3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.sys.bean.CrmAddress;
import com.bdfint.backend.modules.sys.bean.CrmHardwareRequest;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardChangeRequest;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardRequest;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.bean.CrmOrderOperate;
import com.bdfint.backend.modules.sys.bean.CrmSmsRequest;
import com.bdfint.backend.modules.sys.mapper.CrmAddressMapper;
import com.bdfint.backend.modules.sys.mapper.CrmHardwareRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardChangeRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderItemMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderOperateMapper;
import com.bdfint.backend.modules.sys.mapper.CrmSmsRequestMapper;
import com.bdfint.backend.modules.sys.service.CrmOrderItemService;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * 订单Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmOrderItemServiceImpl extends BaseIntServiceImpl<CrmOrderItem> implements CrmOrderItemService {

	@Autowired
	private CrmOrderItemMapper crmOrderItemMapper; 
	
	@Autowired
	private CrmOrderOperateMapper crmOrderOperateMapper;
	
	@Autowired
	private CrmAddressMapper crmAddressMapper;
	
	@Autowired
	private CrmNorthCardRequestMapper crmNorthCardRequestMapper;//北斗卡申请mapper
	
	@Autowired
	private CrmHardwareRequestMapper crmHardwareRequestMapper;//硬件申请mapper
	
	@Autowired
	private CrmSmsRequestMapper crmSmsRequestMapper;//增值服务mapper
	
	@Autowired
	private CrmNorthCardChangeRequestMapper crmNorthCardChangeRequestMapper;//转网mapper
	
	/**
	 * 添加收货地址形成新订单
	 */
	@Override
	public String addAddress(CrmOrderItem crmOrderItem){
		CrmOrderOperate crmOrderOperate = new CrmOrderOperate();
		String sub = "";
		try {
			if(crmOrderItem.getAddressId() > 0){
				CrmAddress record = new CrmAddress();
				record.setId(crmOrderItem.getAddressId());
				Example example = new Example(CrmAddress.class);
				example.createCriteria().andEqualTo("id", crmOrderItem.getAddressId());
				List<CrmAddress> selectByExample = crmAddressMapper.selectByExample(example);
				if(selectByExample.size() == 1){
					crmOrderItem.setUserName(selectByExample.get(0).getConsignee());
					System.out.println(selectByExample.get(0).getConsignee());
					crmOrderItemMapper.addAddress(crmOrderItem);
					crmOrderOperate.setOrderId(crmOrderItem.getOrderId());
					crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_NEW);
					crmOrderOperate.setOperateBy(UserUtils.getUser().getId());
					crmOrderOperateMapper.insert(crmOrderOperate);
					sub = "1";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sub;      
	}
	
	/**
	 * 订单状态为待审核是可删除订单及北斗卡申请
	 */
	@Override
	public Boolean deleteFlag(String orderId) {
		CrmOrderOperate crmOrderOperate = new CrmOrderOperate();
		String sub = "";
		String type = orderId.replaceAll("[^a-zA-Z].*$", "");
		try {
//            CrmOrderItem one = crmOrderItemMapper.selectOne(item);
            //-----查询订单状态，“1”未审核--允许删除订单，其他--不允许删除
            Example example = new Example(CrmOrderItem.class);
            example.or().andEqualTo("orderId",orderId);
            List<CrmOrderItem> items = crmOrderItemMapper.selectByExample(example);
            if(!Collections3.isEmpty(items)){
                String status = items.get(0).getStatus();
                if(status != null && !status.equals("1")) {
                    return false;
                }
            }else {
                return false;
            }

            String str = UserUtils.updateDataToDatabase(CrmOrderItem.class, crmOrderItemMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.ORDER_KEY );
			if(str != null && !str.equals("")){
				crmOrderOperate.setOrderId(orderId);
				crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_DELETE);
				crmOrderOperate.setOperateBy(UserUtils.getUser().getId());
				crmOrderOperateMapper.insert(crmOrderOperate);
				switch (type) {
				case "KSQ":
					UserUtils.updateDataToDatabase(CrmNorthCardRequest.class, crmNorthCardRequestMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.REQUEST_KEY);
					break;
				case "SB":
					UserUtils.updateDataToDatabase(CrmHardwareRequest.class, crmHardwareRequestMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.REQUEST_KEY);
					break;
				case "ZZ":
					UserUtils.updateDataToDatabase(CrmSmsRequest.class, crmSmsRequestMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.REQUEST_KEY);
					break;
				case "ZW":
					UserUtils.updateDataToDatabase(CrmNorthCardChangeRequest.class, crmNorthCardChangeRequestMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.REQUEST_KEY);
					break;
				case "QT":
//					UserUtils.updateDataToDatabase(CrmNorthCardRequest.class, crmNorthCardRequestMapper, orderId, UserUtils.DEL_FLAG, UserUtils.DEL_VAL,UserUtils.REQUEST_KEY);
					break;
				default:
					break;
				}
			}
			return true;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据不同类型表单查询详细信息
	 */
	@Override
	public List<CrmOrderItem> orderDetail(String orderId, Integer goodsId, Integer addressId,String orderType) {
		if(Integer.valueOf(orderType) < 3 ){
			return crmOrderItemMapper.selectMinOrderDetailByPage(orderId, goodsId, addressId,orderType);
		}else{
			return crmOrderItemMapper.selectMaxOrderDetailByPage(orderId, goodsId, orderType);
		}
	}

    /**
     * 填写快递单号
     * @param orderId
     * @param smsOrder
     * @return
     */
    @Override
    public Boolean addSmsOrder(String orderId, String smsOrder) {
        Example example = new Example(CrmOrderItem.class);
        example.or().andEqualTo("orderId",orderId);
        List<CrmOrderItem> crmOrderItems = crmOrderItemMapper.selectByExample(example);
        if(crmOrderItems.size() > 0){
            CrmOrderItem crmOrderItem = new CrmOrderItem();
            crmOrderItem.setEmsOrder(smsOrder);
            crmOrderItemMapper.updateByExampleSelective(crmOrderItem,example);
            String userId = crmOrderItems.get(0).getUserId();
            return orderHandle(orderId,"3",userId);
        }
        return false;
    }


    /**
	 * 订单处理状态
	 * --待操作（
     *   0初始化状态/添加（北斗卡、设备）/转网/增值添加
     *   1绑定北斗卡
     *   2添加快递单号
     *   3完成订单（天 时：分：秒后自动完成）
     *   ）
	 *
	 *   handleStatus ---- 改后的状态
	 */
	@Override
	public Boolean orderHandle(String orderId,String handleStatus,String userId){
		CrmOrderOperate crmOrderOperate = new CrmOrderOperate();
		CrmOrderItem record = new CrmOrderItem();
		String type = orderId.replaceAll("[^a-zA-Z].*$", "");
		
		Example exampleRequest = new Example(CrmOrderItem.class);
		try {
			record.setHandleStatus(handleStatus);
			exampleRequest.createCriteria().andCondition("order_id='"+orderId+"'");
			int updateByExample1 = crmOrderItemMapper.updateByExampleSelective(record, exampleRequest);
			
			crmOrderOperate.setOrderId(orderId);
			if(type.equals("KSQ")){
                if(handleStatus.equals("0") || handleStatus.equals("1")){
                    crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_PUTCARD);
                }else if(handleStatus.equals("2")){
                    crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_BOUNDCARD);
                }
			}else{
				crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_ACCEPT);
			}
            if(handleStatus.equals("3")){
                crmOrderOperate.setOperate(UserUtils.ORDER_OPERATOR_SENDED);
            }
			crmOrderOperate.setOperateBy(userId);
			crmOrderOperate.setOperateTime(new Date());
			crmOrderOperateMapper.insert(crmOrderOperate);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;      
	}
}