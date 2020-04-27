package com.dbs.controller;

import com.dbs.model.SysJjfa;
import com.dbs.model.SysJjfaSon;
import com.dbs.service.impl.ISysJjfaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lsl on 2017/12/22.
 */
@RestController
@RequestMapping("/jjfa")
public class SysJjfaController {

    @Autowired
    private ISysJjfaService sysJjfaService;

    //根据menulId查找解决方案列表


    /**
     * 根据menuId返回唯一的解决方案详情
     * @param menuId
     * @return
     */
    @RequestMapping("/getOneWithSonByMenuId")
    public List<SysJjfa> getOneWithSon(String menuId){
        return sysJjfaService.getOneWithSonByMenuId(menuId);
    }

    /**
     * 获取首页展示的SysJjfa列表
     * @return
     */
    @RequestMapping("/findListInHome")
    public List<SysJjfa> findListInHome(){
        return sysJjfaService.findListInHome();
    }
    /**
     * 根据parentId返回解决方案特点列表
     * @param parentId
     * @return
     */
    @RequestMapping("/getSonListByPatentId")
    public List<SysJjfaSon> getSonList(String parentId){
        return sysJjfaService.findSonListByParentId(Integer.parseInt(parentId));
    }

}
