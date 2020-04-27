package com.dbs.service;

import com.dbs.mapper.SysHzhbMapper;
import com.dbs.model.SysHzhb;
import com.dbs.service.impl.ISysHzhbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lsl on 2017/12/25.
 */
@Service
@Transactional
public class SysHzhbService implements ISysHzhbService {

    @Autowired
    private SysHzhbMapper sysHzhbMapper;

    @Override
    public List<SysHzhb> findAll() {
        return sysHzhbMapper.selectAll();
    }
}
