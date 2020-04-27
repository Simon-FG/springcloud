package com.bdfint.backend.modules.sys.action;

import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bdfint.backend.modules.sys.bean.CrmInvoice;
import com.bdfint.backend.modules.sys.service.CrmInvoiceService;

/**
 * 发票action
 * @author 74091
 *
 * -----------接口----------
 * 		订单分页列表(id/userId/status/orderId /pageNum/pageSize)
 * 		http://localhost:8082/admin/invoice/findListByPage
 * 		订单审核(id、status（2：通过 0：驳回）)
 * 		http://localhost:8082/admin/invoice/audit
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/invoice")
public class CrmInvoiceAction extends BaseAction {
	
	@Autowired
	private CrmInvoiceService crmInvoiceService;
	
	/**
	 * 用户申请方法
	 * @param requestJson
	 * @return
	 */
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	//@RequiresPermissions("sys:invoice:useradd")
	public Boolean add(@RequestBody(required=false) CrmInvoice crmInvoice) {
		return crmInvoiceService.add(crmInvoice) ;
	}
	
	
	/**
	 * 用户修改发票信息
	 * @param crmInvoice
	 * @return
	 */
	@RequestMapping(value = "/userEdit", method = RequestMethod.POST)
	public Boolean edit(@RequestBody(required=false) CrmInvoice crmInvoice) {
		
		return crmInvoiceService.edit(crmInvoice);
	}

	/***
	 * 用户删除发票申请信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/userDelete")
	public int delete(@RequestParam(value = "id",required=false) int id) {
		int res=-1;
		if(id > 0){
			res = crmInvoiceService.delete(id);
		}
		return res;
	}

	/***
	 *	发票详细信息
	 * @param id
	 * @param orderId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public List<CrmInvoice> detail(@RequestParam(value = "orderId",required=false) String orderId) {
		
		return crmInvoiceService.detail(orderId);
	}

	/**
	 * 查询订单分页列表
	 * @param crmInvoice (id/userId/status/orderId)
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/findListByPage")
	public PageInfo<CrmInvoice> findListByPage(CrmInvoice crmInvoice) throws Exception {
		return crmInvoiceService.findListByPage(crmInvoice);
	}
	
}
