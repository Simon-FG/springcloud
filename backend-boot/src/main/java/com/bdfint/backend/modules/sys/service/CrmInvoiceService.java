package com.bdfint.backend.modules.sys.service;

import java.util.List;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmInvoice;
import com.github.pagehelper.PageInfo;

public interface CrmInvoiceService extends BaseIntService<CrmInvoice> {

	public Boolean add(CrmInvoice crmInvoice);
	
	public Boolean audit(CrmInvoice crmInvoice);
	
	public Boolean edit(CrmInvoice crmInvoice);
	
	public int delete(int id);
	
	public List<CrmInvoice> detail(String orderId);

	public PageInfo<CrmInvoice> findListByPage(CrmInvoice crmInvoice) throws Exception;
	
}
