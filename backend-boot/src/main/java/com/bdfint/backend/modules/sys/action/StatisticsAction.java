package com.bdfint.backend.modules.sys.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.service.CrmNorthCardServie;
import com.bdfint.backend.modules.sys.service.DbSmsService;

/**
 * Created by lsl on 2018/1/24.
 */
@RestController
@RequestMapping("{adminPath}/statistics")
public class StatisticsAction extends BaseAction {

    @Autowired
    private CrmNorthCardServie crmNorthCardServie;

   // @Autowired
   // private DbNorthLocationService dbNorthLocationService;

    @Autowired
    private DbSmsService dbSmsService;

    @RequestMapping("/getCountToday")
    public Map<String,Integer> getCountToday(){
        Integer countByUser = crmNorthCardServie.getCountByUser();
        //Integer countTodayByUser = dbNorthLocationService.getCountTodayByUser();
        Integer countTodayByUser1 = dbSmsService.getCountTodayByUser();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("cardNum",countByUser);
        map.put("smsNum",countTodayByUser1);
        map.put("locNum",0);
        return map;
    }
}
