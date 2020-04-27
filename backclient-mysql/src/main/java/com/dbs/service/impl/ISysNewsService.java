package com.dbs.service.impl;

import com.dbs.model.SysNews;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by lsl on 2017/12/25.
 */
public interface ISysNewsService {

    public SysNews getById(Integer id);

    public List<SysNews> findListByStatus(String status);

    public PageInfo<SysNews> findAllByPage(SysNews sysNews);


}
