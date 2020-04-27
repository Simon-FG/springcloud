package com.bdfint.backend.modules.operator.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.operator.service.OrderOperatorService;

@RestController
@RequestMapping(value = "${adminPath}/orderOperator")

public class OrderOperatroAction {
	
	@Autowired
	private OrderOperatorService orderOperatorService;
	
	/**
	 * 订单审核修改订单状态
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/reviewOrder", method = RequestMethod.POST)
//	@RequiresPermissions("sys:orderOperator:reviewOrder")
	public Boolean reviewOrder(@RequestParam(value = "orderId",required=false) String orderId,@RequestParam(value = "status",required=false) String status,@RequestParam(value = "content",required=false) String content){

		return orderOperatorService.updateStatus(orderId,status,content);
	}

}
