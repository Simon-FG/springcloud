package com.dbs.controller;

import com.dbs.model.SysHzhb;
import com.dbs.service.SysHzhbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2017/12/25.
 */
@RestController
@RequestMapping("/hzhb")
public class SysHzhdController {

    @Autowired
    private SysHzhbService sysHzhbService;

    /**
     * 获取所有SysHzhb记录
     * @return
     */
    @RequestMapping("/findAll")
    public List<SysHzhb> findAll(){
        return sysHzhbService.findAll();
    }

  
}
