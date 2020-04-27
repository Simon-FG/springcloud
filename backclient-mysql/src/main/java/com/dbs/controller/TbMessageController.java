package com.dbs.controller;

import com.dbs.model.TbMessage;
import com.dbs.service.impl.ITbMessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2018/1/3.
 */
@RestController
@RequestMapping("/message")
public class TbMessageController {

    @Autowired
    private ITbMessageService tbMessageService;

    @RequestMapping("/getOneById")
    public TbMessage getOneById(Integer id){
        return tbMessageService.getOne(id);
    }

    @RequestMapping("/findListByVo")
    public Object findListByVo(TbMessage tbMessage, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"id desc");
        List<TbMessage> listByVo = tbMessageService.findListByVo(tbMessage);
        return new PageInfo<>(listByVo);
    }

    @RequestMapping("/getCountNotRead")
    public Integer getCountNotRead(TbMessage tbMessage){
        return tbMessageService.getCountNotRead(tbMessage);
    }

    @RequestMapping("/addOne")
    public Boolean addOne(TbMessage tbMessage){
        return tbMessageService.addOne(tbMessage);
    }

    @RequestMapping("/editOne")
    public Boolean editOne(TbMessage tbMessage){
        return tbMessageService.editOne(tbMessage);
    }

    @RequestMapping("/setReaded")
    public Boolean setReaded(Integer ... id){
        return tbMessageService.setReaded(id);
    }

    @RequestMapping("/delById")
    public Boolean delById(Integer ... id){
        return tbMessageService.delById(id);
    }
}
