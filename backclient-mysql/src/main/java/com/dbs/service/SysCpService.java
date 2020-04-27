package com.dbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbs.mapper.SysCpMapper;
import com.dbs.model.SysCp;
import com.dbs.service.impl.ISysCpService;
@Service
@Transactional
public class SysCpService implements ISysCpService {
	@Autowired
	private SysCpMapper sysCpMapper;
	private List<SysCp> cpList;
    
    @Override
    public List<SysCp> getCpDetail(String menuId){
    	
        return sysCpMapper.queryCpdetailByPage(menuId);
    };
    
    /***
     * 产品查询方法
     */
	@Override
	public List<SysCp> execute(Integer cpId, String cpName) {
		Map queryMap = new HashMap();
		
		if(cpId != null && !cpId.equals("")){
		    queryMap.put("cpId", cpId);
		}
		if(cpName != null && !cpName.equals("")){
			queryMap.put("cpName", cpName);
		}
		cpList = sysCpMapper.queryCpByPage(queryMap);
		
		return cpList;
	} 
	
	/***
     * 首页显示
     */
	@Override
	public List<SysCp> queryList() {
		return sysCpMapper.queryList();
	} 
}
