package com.bdfint.backend.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.bdfint.backend.framework.util.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.sys.bean.CrmInvoice;
import com.bdfint.backend.modules.sys.mapper.CrmInvoiceMapper;
import com.bdfint.backend.modules.sys.service.CrmInvoiceService;
import tk.mybatis.mapper.entity.Example;

/**
 * 发票Service
 *
 * @author hlw
 * @version 2016/7/28
 */
@Service
@Transactional
public class CrmInvoiceImpl extends BaseIntServiceImpl<CrmInvoice> implements CrmInvoiceService {
	
	@Autowired
	private CrmInvoiceMapper crmInvoiceMapper; 
	
	/***
	 * 用户提交开票申请
	 * @return
	 */
	@Override
	@Transactional
	public Boolean add(CrmInvoice crmInvoice) {
		
		try {
			super.save(crmInvoice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/***
	 * 管理员审核开票信息
	 * @return
	 */
	@Override
	public Boolean audit(CrmInvoice crmInvoice) {
			Example exampleRequest = new Example(CrmInvoice.class);
			exampleRequest.createCriteria().andEqualTo("id", crmInvoice.getId());
			List<CrmInvoice> selectByExampleRequest = crmInvoiceMapper.selectByExample(exampleRequest);
			if(selectByExampleRequest.size()==1)
			{
				CrmInvoice crmInvoice1 = selectByExampleRequest.get(0);
				try {
					crmInvoice1.setStatus(crmInvoice.getStatus());
					crmInvoice1.setEndTime(new Date());
					exampleRequest.createCriteria().andCondition("id='"+crmInvoice1.getId()+"'");
					int updateByExample1 = crmInvoiceMapper.updateByExample(crmInvoice1, exampleRequest);
					if(updateByExample1 == 1){
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		return false;
	}
	
	
	
	/***
	 * 用户修改提交信息
	 * @return
	 */
	@Override
	public Boolean edit(CrmInvoice crmInvoice) {
		CrmInvoice invoice = new CrmInvoice();
		try {
			crmInvoiceMapper.updateByPrimaryKeySelective(crmInvoice);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return true;
	}
	
	
	/***
	 * 申请为办结前用户删除申请信息
	 * @return
	 */
	@SuppressWarnings("finally")
	@Override
	public int delete(int id) {
		int res=-1;
		try {
			res=crmInvoiceMapper.deleteByPrimaryKey(id);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			return res;
		}
		
	}

	/***
	 * 查看详细信息
	 * @return
	 */
	@Override
	public List<CrmInvoice> detail(String orderId) {
		Example example = new Example(CrmInvoice.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		return crmInvoiceMapper.selectByExample(example);
	}

	@Override
	public PageInfo<CrmInvoice> findListByPage(CrmInvoice crmInvoice) throws Exception {
        Example example = new Example(CrmInvoice.class);
        Example.Criteria or = example.or();
        if(crmInvoice.getId() != 0){
            or.andEqualTo("id", crmInvoice.getId());
        }else{
            if(StringUtils.isNotBlank(crmInvoice.getUserId())){
                or.andEqualTo("userId", crmInvoice.getUserId());
            }
            if(StringUtils.isNotBlank(crmInvoice.getStatus())){
                or.andEqualTo("status", crmInvoice.getStatus());
            }
            if(StringUtils.isNotBlank(crmInvoice.getOrderId())){
                or.andEqualTo("orderId", crmInvoice.getOrderId());
            }
        }
        example.setOrderByClause("start_time desc");
        return getPage(crmInvoice,example);
	}


}