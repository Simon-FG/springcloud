package com.dbs.service;

import com.dbs.mapper.SysNewsMapper;
import com.dbs.model.SysNews;
import com.dbs.service.impl.ISysNewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lsl on 2017/12/25.
 */
@Service
@Transactional
public class SysNewsService implements ISysNewsService {

    @Autowired
    private SysNewsMapper sysNewsMapper;

    @Override
    public SysNews getById(Integer id) {
        if(null != id){
            return sysNewsMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<SysNews> findListByStatus(String status) {
        if(null != status && !"".equals(status)){
            return sysNewsMapper.selectByStatus(status);
        }else {
            return sysNewsMapper.selectAll();
        }
    }

    @Override
    public PageInfo<SysNews> findAllByPage(SysNews sysNews) {
        int pageSize = sysNews.getPageSize() == 0 ? 10 : sysNews.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(sysNews.getPageNum(), pageSize);
        }
        List<SysNews> newses = sysNewsMapper.selectAll();
        PageInfo<SysNews> page = new PageInfo<>(newses);
        page.setPageNum(sysNews.getPageNum());
        page.setPageSize(pageSize);
        return page;
    }

}
