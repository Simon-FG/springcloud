package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.bean.DbSms;
import com.bdfint.backend.modules.sys.service.DbSmsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lsl on 2018/1/19.
 *
 * ------接口-------
 *      分页列表（type、cardId、startAt、endAt）
 *      http://localhost:8082/admin/dbSms/findListByPage
 *      数据统计（短报文type:"0" 分发type:"1" 、cardId、startAt、endAt、ymd）
 *      http://localhost:8082/admin/dbSms/getCount
 */
@RestController
@RequestMapping("{adminPath}/dbSms")
public class DbSmsAction extends BaseAction {

    @Autowired
    private DbSmsService dbSmsService;

    @RequestMapping("/findListByPage")
    public PageInfo<DbSms> findListByPage(DbSms dbSms) throws Exception {
        return dbSmsService.findListByPage(dbSms);
    }

    @RequestMapping("/getCount")
    public Map<String,Object> getCount(DbSms dbSms){
        return dbSmsService.getCount(dbSms);
    }

}
