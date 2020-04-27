package com.dbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbs.mapper.SysYjMapper;
import com.dbs.model.SysYj;
import com.dbs.service.impl.ISysYjService;
@Service
@Transactional
public class SysYjService implements ISysYjService {
	@Autowired
	private SysYjMapper sysYjMapper; 
	private List<SysYj> yjList;
    
    @Override
    public List<SysYj> getYjDetail(Integer yjId){
        return sysYjMapper.queryYjdetailByPage(yjId);
    };
    
    /***
     * 产品查询方法
     */
	@Override
	public List<SysYj> execute(Integer yjId, String yjName) {
		Map queryMap = new HashMap();
		
		if(yjId != null && !yjId.equals("")){
		    queryMap.put("yjId", yjId);
		}
		if(yjName != null && !yjName.equals("")){
			queryMap.put("yjName", yjName);
		}
		yjList = sysYjMapper.queryYjByPage(queryMap);
		
		return yjList;
	}

}
