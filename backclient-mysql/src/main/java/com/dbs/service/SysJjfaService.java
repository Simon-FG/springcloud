package com.dbs.service;

import com.dbs.mapper.SysJjfaMapper;
import com.dbs.mapper.SysJjfaSonMapper;
import com.dbs.model.SysJjfa;
import com.dbs.model.SysJjfaSon;
import com.dbs.service.impl.ISysJjfaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lsl on 2017/12/22.
 */
@Service
@Transactional
public class SysJjfaService implements ISysJjfaService {

    @Autowired
    private SysJjfaMapper sysJjfaMapper;

    @Autowired
    private SysJjfaSonMapper sysJjfaSonMapper;

    @Override
    public List<SysJjfa> getOneWithSonByMenuId(String menuId){
        return sysJjfaMapper.selectOneWithSonByMenuId(menuId);
    }

    @Override
    public List<SysJjfa> findListInHome() {
        return sysJjfaMapper.selectListInHome();
    }

    @Override
    public List<SysJjfaSon> findSonListByParentId(Integer parentId) {
        return sysJjfaSonMapper.selectByParentId(parentId);
    }

}
