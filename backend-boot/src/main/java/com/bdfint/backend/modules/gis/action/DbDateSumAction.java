package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.gis.bean.DbDateSum;
import com.bdfint.backend.modules.gis.service.DbDateSumService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.bean.DbSms;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardMapper;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsl on 2018/4/8.
 *
 * -------接口-----
 *  数据统计（cardId、startTime、endTime、type）
 *  admin/dateSum/getSumList
 */
@RestController
@RequestMapping("{adminPath}/dateSum")
public class DbDateSumAction extends BaseAction {
    @Autowired
    private DbDateSumService sumService;
    
    @Autowired
    private CrmNorthCardMapper cardMapper;
    
    
    @RequestMapping("/getSum")
    public Map<String,Object> getCount() throws Exception{
    	HashMap<String, Integer> map = new HashMap<>();
        List<CrmNorthCard> cardList = new ArrayList<>();
        List list = new ArrayList<>();
        if(UserUtils.isYWorYY()){
        	Example example = new Example(CrmNorthCard.class);
            Example.Criteria or = example.or();
            or.andNotEqualTo("delFlag","1");
            cardList = cardMapper.selectByExample(example);//查询全部可用北斗卡
            for(int i = 0; i < cardList.size(); i++){
            	list.add(cardList.get(i).getCardId());
            }
            
        }else{
        	Example example = new Example(CrmNorthCard.class);
            Example.Criteria or = example.or();
            String userId = UserUtils.getUserId();
            or.andEqualTo("userId",userId);
            or.andNotEqualTo("delFlag","1");
            cardList = cardMapper.selectByExample(example);
            for(int i = 0; i < cardList.size(); i++){
            	list.add(cardList.get(i).getCardId());
            }
        }
        return sumService.getDaySum(UserUtils.isYWorYY(),list);
    }

    @RequestMapping("/getSumList")
    public PageInfo<DbDateSum> getSumList(DbDateSum dateSum) throws Exception {
        return sumService.getSumList(dateSum);
    }
}
