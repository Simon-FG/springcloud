package com.bdfint.backend.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.sys.bean.SysCompanyManager;
import com.bdfint.backend.modules.sys.mapper.SysCompanyManagerMapper;
import com.bdfint.backend.modules.sys.service.SysCompanyManagerService;

import tk.mybatis.mapper.entity.Example;

/**
 * 公司名称模糊查询Service
 *
 * @author hlw
 * @version 2016/7/28
 */
@Service
@Transactional
public class SysCompanyManagerServiceImpl extends BaseIntServiceImpl<SysCompanyManager> implements SysCompanyManagerService {
	
	@Autowired
	private SysCompanyManagerMapper companyManagerMapper;

	/**
	 * 根据企业名称模糊查询
	 */
    @Override
	public List<SysCompanyManager> queryLikeCompany(String company) {
		
    	Example example = new Example(SysCompanyManager.class);
        example.createCriteria().andLike("company", "%" + company + "%");
		return companyManagerMapper.selectByExample(example);
	}
    
    /**
     * 根据企业关联用户ID查询企业名称
     */
    @Override
    public String queryCompanyName(String userId){
    	String company = null;
    	Example example = new Example(SysCompanyManager.class);
    	example.createCriteria().andEqualTo("regUserid", userId);
		List<SysCompanyManager> selectByExample = companyManagerMapper.selectByExample(example);
		if(selectByExample.size() == 1){
			company = selectByExample.get(0).getCompany();
		}
		return company;
    }

}