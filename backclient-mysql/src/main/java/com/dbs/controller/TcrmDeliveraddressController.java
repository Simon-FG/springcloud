package com.dbs.controller;

import com.dbs.model.TcrmDeliveraddress;
import com.dbs.service.impl.ITcrmDeliveraddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2018/1/2.
 */
@RestController
@RequestMapping("/deliveraddress")
public class TcrmDeliveraddressController {

    @Autowired
    private ITcrmDeliveraddressService tcrmDeliveraddressService;

    /**
     * 根据id查找TcrmDeliveraddress记录
     * @param id
     * @return
     */
    @RequestMapping("/getOneById")
    public TcrmDeliveraddress getOneById(Integer id){
        return tcrmDeliveraddressService.getById(id);
    }

    /**
     * 根据userId查找TcrmDeliveraddress记录列表
     * @param userId
     * @return
     */
    @RequestMapping("/findListByUser")
    public List<TcrmDeliveraddress> findListByUser(Integer userId){
        return tcrmDeliveraddressService.findListByUser(userId);
    }

    /**
     * 添加一条TcrmDeliveraddress记录
     * @param tcrmDeliveraddress
     * @return
     */
    @RequestMapping("/addOne")
    public Boolean addOne(TcrmDeliveraddress tcrmDeliveraddress){
        return tcrmDeliveraddressService.addOne(tcrmDeliveraddress);
    }

    /**
     * 跟新一条TcrmDeliveraddress记录
     * @param tcrmDeliveraddress
     * @return
     */
    @RequestMapping("/editOne")
    public Boolean editOne(TcrmDeliveraddress tcrmDeliveraddress){
        return tcrmDeliveraddressService.editOne(tcrmDeliveraddress);
    }

    /**
     * 根据id删除TcrmDeliveraddress记录
     * @param id
     * @return
     */
    @RequestMapping("/delById")
    public Boolean delById(Integer ... id){
        return tcrmDeliveraddressService.delById(id);
    }
}
