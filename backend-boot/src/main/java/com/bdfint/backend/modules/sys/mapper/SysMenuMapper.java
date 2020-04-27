package com.bdfint.backend.modules.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.SysMenu;

@Mapper
@Repository
public interface SysMenuMapper extends CommonMapper<SysMenu>{
    
    public  List<SysMenu> queryMenuByPage(Map queryMap);

}