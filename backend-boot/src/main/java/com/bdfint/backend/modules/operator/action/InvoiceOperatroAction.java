package com.bdfint.backend.modules.operator.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.sys.bean.CrmInvoice;
import com.bdfint.backend.modules.sys.service.CrmInvoiceService;

@RestController
@RequestMapping(value = "${adminPath}/invoiceOperator")

public class InvoiceOperatroAction {
	
	@Autowired
	private CrmInvoiceService crmInvoiceService;
	/**
	 * 管理员审核(id、status（2：通过 0：驳回）)
	 * @param crmInvoice
	 * @return
	 */
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Boolean audit(CrmInvoice crmInvoice) {
		
		return crmInvoiceService.audit(crmInvoice);
	}

}
