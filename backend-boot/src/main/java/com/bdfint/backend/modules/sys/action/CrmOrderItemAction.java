package com.bdfint.backend.modules.sys.action;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.operator.service.OrderOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.service.CrmOrderItemService;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 订单action
 * @author 74091
 *
 *      ---接口---
 *      填写快递单号（orderId、smsOrder）
 *      http://localhost:8082/admin/orderItem/addSmsOrder
 *      完成订单（orderId）
 *      http://localhost:8082/admin/orderItem/finishOrder
 */
@RestController
@RequestMapping(value = "${adminPath}/orderItem")
public class CrmOrderItemAction extends BaseAction {
	
	@Autowired
	private CrmOrderItemService crmOrderItemService;

    @Autowired
    private OrderOperatorService orderOperatorService;
	
	/**
	 * 添加收货地址并提交
	 * @param requestJson
	 * @return
	 */
	@RequestMapping(value = "/addAddress", method = RequestMethod.POST) 
//	//@RequiresPermissions("sys:orderItem:addAddress")
    public String addAddress(CrmOrderItem crmOrderItem) {     
		return crmOrderItemService.addAddress(crmOrderItem);
    }
	
	/**
	 * 订单删除操作
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Boolean delete(@RequestParam(value = "orderId",required=false) String orderId){
		
		return crmOrderItemService.deleteFlag(orderId);
	}
	
	/**
     * 订单列表
     * @return PageInfo<User>
     */
    @RequestMapping(value = "orderList")
    public PageInfo<CrmOrderItem> list( CrmOrderItem crmOrderItem) throws Exception {
    	Date date = null;
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Example example = new Example(CrmOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(crmOrderItem.getOrderBy())) {
            example.setOrderByClause(crmOrderItem.getOrderBy());
        } else {
            example.setOrderByClause("id DESC");
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getOrderId())) {
            criteria.andCondition("order_id" + "=", crmOrderItem.getOrderId());
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getOrderType())) {
            criteria.andCondition("order_type" + "=", crmOrderItem.getOrderType());
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getStatus())) {
            criteria.andCondition("status" + "=", crmOrderItem.getStatus());
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getHandleStatus())) {
            criteria.andCondition("handle_status" + "=", crmOrderItem.getHandleStatus());
        }else {
            if(StringUtils.isNotEmpty(crmOrderItem.getFlag()) && crmOrderItem.getFlag().equals("1")){
                criteria.andCondition("handle_status" + "!=", 3);
            }
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getUserId())) {
            criteria.andCondition("user_id"+"=",crmOrderItem.getUserId());
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getStrTime()) && !crmOrderItem.getStrTime().equals("undefined")) {
        	date = format.parse(crmOrderItem.getStrTime());
            criteria.andCondition("start_time" + ">=",date);
        }
        if (StringUtils.isNotEmpty(crmOrderItem.getEndTime())) {
        	date = format.parse(crmOrderItem.getEndTime());
        	Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
            Date tomorrow = c.getTime(); 
            criteria.andCondition("start_time" + "<=",tomorrow);
        }
        PageInfo<CrmOrderItem> page = crmOrderItemService.getPage(crmOrderItem, example);
        
        return page;
    }

    /**
     * 订单详细信息
     * @return PageInfo<User>
     */

    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    public List<CrmOrderItem> Detail(@RequestParam String orderId,@RequestParam Integer goodsId,@RequestParam Integer addressId,@RequestParam String orderType )throws Exception {
		
    	return crmOrderItemService.orderDetail(orderId, goodsId, addressId,orderType);
    }

    /**
     * 填写快递单号
     * @param orderId
     * @param smsOrder
     * @return
     */
    @RequestMapping("/addSmsOrder")
    public Boolean addSmsOrder(@RequestParam String orderId, @RequestParam String smsOrder){
        return crmOrderItemService.addSmsOrder(orderId,smsOrder);
    }

    /**
     * 完成订单(已收货)
     * @param orderId
     * @return
     */
    @RequestMapping("/finishOrder")
    public Boolean finishOrder(@RequestParam String orderId){
        return orderOperatorService.updateStatus(orderId,"3",null);
    }
}
