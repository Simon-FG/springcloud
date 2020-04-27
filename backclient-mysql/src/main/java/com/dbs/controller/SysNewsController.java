package com.dbs.controller;

import com.dbs.model.SysNews;
import com.dbs.service.impl.ISysNewsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2017/12/25.
 */
@RestController
@RequestMapping("/news")
public class SysNewsController {

    @Autowired
    private ISysNewsService sysNewsService;

    /**
     * 根据id查询SysNews记录
     * @param id
     * @return
     */
    @RequestMapping("/getOneById")
    public SysNews getOneById(Integer id){
        return sysNewsService.getById(id);
    }

    /**
     * 根据status查询SysNew列表（0：过期  1：生效  ""/null：查询所有）
     * @param status
     * @return
     */
    @RequestMapping("/findListByStatus")
    public List<SysNews> findListByStatus(String status){
        return sysNewsService.findListByStatus(status);
    }


    /**
     * 系统消息的分页列表（pageNum/pageSize）
     * @param sysNews
     * @return
     */
    @RequestMapping("/findListByPage")
    public PageInfo<SysNews> findListByPage(SysNews sysNews){
        return sysNewsService.findAllByPage(sysNews);
    }

}
