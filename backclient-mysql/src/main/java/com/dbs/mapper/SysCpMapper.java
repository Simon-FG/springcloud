package com.dbs.mapper;

import java.util.List;
import java.util.Map;
import com.dbs.model.SysCp;

public interface SysCpMapper {
    
    public  List<SysCp> queryCpByPage(Map queryMap); 
    
    
    public List<SysCp> queryCpdetailByPage(String menuId);
    
    public List<SysCp> queryList();
    
}