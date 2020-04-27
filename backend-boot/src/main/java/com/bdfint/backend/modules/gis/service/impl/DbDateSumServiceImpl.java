package com.bdfint.backend.modules.gis.service.impl;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.gis.bean.DbDateSum;
import com.bdfint.backend.modules.gis.bean.DbNorthLocation;
import com.bdfint.backend.modules.gis.mapper.DbDateSumMapper;
import com.bdfint.backend.modules.gis.mapper.DbNorthLocationMapper;
import com.bdfint.backend.modules.gis.mapper.GisSmsMapper;
import com.bdfint.backend.modules.gis.service.DbDateSumService;
import com.bdfint.backend.modules.gis.service.DbNorthLocationService;
import com.bdfint.backend.modules.gis.service.GisSmsService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.bean.DbWarning;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardMapper;
import com.bdfint.backend.modules.sys.service.CrmNorthCardServie;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.validation.Valid;

/**
 * Created by lsl on 2018/4/8.
 */
@Service
public class DbDateSumServiceImpl extends BasePgServiceImpl<DbDateSum> implements DbDateSumService{

    @Autowired
    private DbDateSumMapper dateSumMapper;

    @Autowired
    private DbNorthLocationService locationService;

    @Autowired
    private GisSmsService smsService;

    @Autowired
    private CrmNorthCardServie cardServie;
    
    @Autowired
    private CrmNorthCardMapper cardMapper;
    
    @Autowired
    private DbNorthLocationMapper locationMapper;
    
    @Autowired
    private GisSmsMapper smsMapper;

    @Override
    @TargetDataSource("pg")
    public Boolean addDaySum(DbDateSum dateSum){
        return dateSumMapper.insertSelective(dateSum) == 1;
    }

    @Override
    @TargetDataSource("pg")
    public Boolean addDaySumList(List<DbDateSum> list){
        return dateSumMapper.addBatch(list) == list.size();
    }
    
    @Override
    @TargetDataSource("pg")
    public Map getDaySum(Boolean role, List list) throws Exception{
        HashMap<String, Integer> map = new HashMap<>();
        if(role){
        	List nullList = null;
            int location = locationMapper.getPositionReportNum(nullList);
            int sms = smsMapper.getSmsReportNum(nullList);
            map.put("cardNum", list.size());
            map.put("locationNum", location);
            map.put("smsNum", sms);
            System.out.println(list.size());//北斗卡数量
            System.out.println(location);//通信数量
            System.out.println(sms);//sms数量
        }else{
            int location = 0;
            int sms = 0;
            if(list.size() != 0){
                sms = smsMapper.getSmsReportNum(list);
                location = locationMapper.getPositionReportNum(list);
            }
            System.out.println(list.size());//北斗卡数量
            System.out.println(location);//通信数量
            System.out.println(sms);
            map.put("cardNum", list.size());
            map.put("locationNum", location);
            map.put("smsNum", sms);
        }
        return map;
    }

    @Override
    @TargetDataSource("pg")
    public PageInfo<DbDateSum> getSumList(DbDateSum sum) throws Exception {
        Example example = new Example(DbDateSum.class);
        Example.Criteria or = example.or();
        sum.setPageSize(Integer.MAX_VALUE);
        Date startTime = sum.getStartTime();
        Date endTime = sum.getEndTime();
        String cardId = sum.getCardId();
        String type = sum.getType();
        String msgType = sum.getMsgType();
        if(startTime != null){
            or.andGreaterThanOrEqualTo("endTime",startTime);
        }
        if(endTime != null){
            or.andLessThanOrEqualTo("startTime",endTime);
        }
        if(StringUtils.isNotBlank(cardId)){
            or.andEqualTo("cardId",cardId);
        }
        if(StringUtils.isNotBlank(type)){
            or.andEqualTo("type",type);
        }
        if(StringUtils.isNotBlank(msgType)){
            or.andEqualTo("msgType",msgType);
        }
        return getPage(sum, example);
    }
}
