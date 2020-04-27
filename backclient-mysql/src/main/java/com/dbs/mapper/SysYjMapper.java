package com.dbs.mapper;

import java.util.List;
import java.util.Map;

import com.dbs.model.SysYj;

public interface SysYjMapper {
    
    public List<SysYj> queryYjByPage(Map queryMap); 
    
    public List<SysYj> queryYjdetailByPage(Integer yjId);
}