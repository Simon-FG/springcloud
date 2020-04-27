package com.bdfint.backend.framework.schedule;

import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.util.DateUtils;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.gis.bean.DbDateSum;
import com.bdfint.backend.modules.gis.bean.DbNorthLocation;
import com.bdfint.backend.modules.gis.bean.GisSms;
import com.bdfint.backend.modules.gis.service.DbDateSumService;
import com.bdfint.backend.modules.gis.service.DbNorthLocationService;
import com.bdfint.backend.modules.gis.service.GisSmsService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.service.CrmNorthCardServie;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bdfint.backend.framework.util.DateUtils.getDayStartAndEnd;

/**
 * Created by lsl on 2018/4/4.
 */
@Component
public class ScheduledService {

    public static final String DAY_SUM_LOCATION = "1";
    public static final String DAY_SUM_SMS_SEND = "2";
    public static final String DAY_SUM_SMS_RECEIVE = "3";

    @Autowired
    private CrmNorthCardServie cardServie;

    @Autowired
    private DbNorthLocationService locationService;

    @Autowired
    private GisSmsService smsService;

    @Autowired
    private DbDateSumService sumService;

    @Scheduled(cron = "0 0 3 * * ?")
    public void scheduled() throws Exception {
        System.out.println("=====>>>>>使用fixedRate{}" + System.currentTimeMillis());
        String[] dayStartAndEnd = getDayStartAndEnd(-1);
        DbNorthLocation location = new DbNorthLocation();
        location.setStartTime(dayStartAndEnd[0]);
        location.setEndTime(dayStartAndEnd[1]);
        GisSms gisSms = new GisSms();
        gisSms.setStartTime(dayStartAndEnd[0]);
        gisSms.setEndTime(dayStartAndEnd[1]);

        //------获取card列表
        List<CrmNorthCard> cards = cardServie.findList();
        ArrayList<DbDateSum> list = new ArrayList<>();
        for(CrmNorthCard northCard: cards){
            //------获取该card当日位置上报数量
            String cardId = northCard.getCardId();
            location.setHX(cardId);
            int daySum = locationService.getDaySum(location);

            //------获取card当日通信数量(send)
            gisSms.setCardId(cardId);
            gisSms.setToCardId(null);
            int daySum1 = smsService.getDaySum(gisSms);

            //------获取card当日通信数量(receive)
            gisSms.setCardId(null);
            gisSms.setToCardId(cardId);
            int daySum2 = smsService.getDaySum(gisSms);

            //-----生成数据统计条目，加入列表中
            DbDateSum dbDateSum = new DbDateSum();
            DbDateSum dbDateSum1 = new DbDateSum();
            DbDateSum dbDateSum2 = new DbDateSum();
            dbDateSum.setCardId(cardId);
            dbDateSum1.setCardId(cardId);
            dbDateSum2.setCardId(cardId);
            dbDateSum.setStartTime(DateUtils.parseDate(dayStartAndEnd[0]));
            dbDateSum1.setStartTime(DateUtils.parseDate(dayStartAndEnd[0]));
            dbDateSum2.setStartTime(DateUtils.parseDate(dayStartAndEnd[0]));
            dbDateSum.setEndTime(DateUtils.parseDate(dayStartAndEnd[1]));
            dbDateSum1.setEndTime(DateUtils.parseDate(dayStartAndEnd[1]));
            dbDateSum2.setEndTime(DateUtils.parseDate(dayStartAndEnd[1]));
            dbDateSum.setTotal(daySum);
            dbDateSum1.setTotal(daySum1);
            dbDateSum2.setTotal(daySum2);
            dbDateSum.setType(DAY_SUM_LOCATION);
            dbDateSum1.setType(DAY_SUM_SMS_SEND);
            dbDateSum2.setType(DAY_SUM_SMS_RECEIVE);
            list.add(dbDateSum);
            list.add(dbDateSum1);
            list.add(dbDateSum2);
        }
        //------批量插入统计表中
        sumService.addDaySumList(list);
    }


//    @Scheduled(fixedRate = 5000)
//    public void scheduled1() {
////        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
//        System.out.println("=====>>>>>使用fixedRate{}" + System.currentTimeMillis());
//    }
//    @Scheduled(fixedDelay = 5000)
//    public void scheduled2() {
////        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
//        System.out.println("=====>>>>>fixedDelay{}" + System.currentTimeMillis());
//    }
}
