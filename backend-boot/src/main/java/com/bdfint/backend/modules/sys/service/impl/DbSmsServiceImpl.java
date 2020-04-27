package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.DateUtils;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.DbSms;
import com.bdfint.backend.modules.sys.service.DbSmsService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by lsl on 2018/1/19.
 */
@Service
public class DbSmsServiceImpl extends BaseIntServiceImpl<DbSms> implements DbSmsService {

    private final String YEAR = "year";
    private final String MONTH = "month";
    private final String MONTH_START = "-01 00:00:00";
    private final String MONTH_END = "-31 24:59:59";
    private final String DAY_START = " 00:00:00";
    private final String DAY_END = " 24:59:59";

    @Override
    public PageInfo<DbSms> findListByPage(DbSms dbSms) throws Exception {
        Example example = new Example(DbSms.class);
        example.setOrderByClause("create_time desc");
        Example.Criteria or = example.or();
        Example.Criteria or2 = example.or();
        or.andEqualTo("userId", UserUtils.getUserId());
        or2.andEqualTo("userId", UserUtils.getUserId());
        if(StringUtils.isNotBlank(dbSms.getType())){
            or.andEqualTo("type",dbSms.getType());
            or2.andEqualTo("type",dbSms.getType());
        }
        if(StringUtils.isNotBlank(dbSms.getStartAt())){
            or.andGreaterThanOrEqualTo("createTime",dbSms.getStartAt());
            or2.andGreaterThanOrEqualTo("createTime",dbSms.getStartAt());
        }
        if(StringUtils.isNotBlank(dbSms.getEndAt())){
            or.andLessThanOrEqualTo("createTime",dbSms.getEndAt());
            or2.andLessThanOrEqualTo("createTime",dbSms.getEndAt());
        }
        if(StringUtils.isNotBlank(dbSms.getCardId())){
            or.andEqualTo("sendCardId",dbSms.getCardId());
            or2.andEqualTo("toCardId",dbSms.getCardId());
        }
        if(StringUtils.isNotBlank(dbSms.getMobile())){
            or.andEqualTo("toMobile",dbSms.getMobile());
            or2.andEqualTo("sendMobile",dbSms.getMobile());
        }
        return getPage(dbSms,example);
    }

    @Override
    public Integer getCountTodayByUser() {
        Date date = new Date();
        String d1 = DateUtils.formatDate(date) + " 00:00:00";
        String d2 = DateUtils.formatDateTime(date);
        Example example = new Example(DbSms.class);
        Example.Criteria or = example.or();
        or.andEqualTo("userId",UserUtils.getUserId());
        or.andBetween("createTime",d1,d2);
        return mapper.selectCountByExample(example);
    }

    @Override
    public Map<String,Object> getCount(DbSms dbSms) {
        String cardId = dbSms.getCardId();
        String type = dbSms.getType();
        String startAt = dbSms.getStartAt();
        String endAt = dbSms.getEndAt();
        String ymd = dbSms.getYmd();

        String year = DateUtils.getYear();
        String month = DateUtils.getMonth();
        String day = DateUtils.getDay();
        int mon = Integer.parseInt(month);
        int d = Integer.parseInt(day);


        LinkedHashMap<String, Object> m = new LinkedHashMap<>();
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        LinkedHashMap<String, Long> map2 = new LinkedHashMap<>();
        ArrayList<Map<String, Long>> list = new ArrayList<>();
        m.put("mapList",list);
        list.add(map);
        list.add(map2);

        if(StringUtils.isNotBlank(cardId) && StringUtils.isNotBlank(type)){
            if(StringUtils.isNotBlank(ymd) && ymd.equals(YEAR)){   //-----------year
                if(type.equals("0")){
                    int t1 = count(year + "-01" + MONTH_START, DateUtils.formatDateTime(new Date()), cardId,"1");
                    int t2 = count(year + "-01" + MONTH_START, DateUtils.formatDateTime(new Date()), cardId,"2");
                    m.put("total1",(long)t1);
                    m.put("total2",(long)t2);

                    for(int i=1; i<=mon; i++){
                        String d1 = year+"-"+toAdd0(i)+MONTH_START;
                        String d2 = year+"-"+toAdd0(i)+MONTH_END;

                        int num = count(d1,d2,cardId,"1");
                        int num2 = count(d1,d2,cardId,"2");
                        map.put(year+"-"+toAdd0(i), (long) num);
                        map2.put(year+"-"+toAdd0(i), (long) num2);
                    }
                }else {
                    int t1 = count(year + "-01" + MONTH_START, DateUtils.formatDateTime(new Date()), cardId,"3");
                    int t2 = count(year + "-01" + MONTH_START, DateUtils.formatDateTime(new Date()), cardId,"4");
                    m.put("total1",(long)t1);
                    m.put("total2",(long)t2);

                    for(int i=1; i<=mon; i++){
                        String d1 = year+"-"+toAdd0(i)+MONTH_START;
                        String d2 = year+"-"+toAdd0(i)+MONTH_END;

                        int num = count(d1,d2,cardId,"3");
                        int num2 = count(d1,d2,cardId,"4");
                        map.put(year+"-"+toAdd0(i), (long) num);
                        map2.put(year+"-"+toAdd0(i), (long) num2);
                    }
                }

            }else if(StringUtils.isNotBlank(ymd) && ymd.equals(MONTH)){   //---------month
                if(type.equals("0")) {
                    int t1 = count(year + month + MONTH_START, DateUtils.formatDateTime(new Date()), cardId, "1");
                    int t2 = count(year + month + MONTH_START, DateUtils.formatDateTime(new Date()), cardId, "2");
                    m.put("total1", (long) t1);
                    m.put("total2", (long) t2);

                    for (int i = 1; i <= d; i++) {
                        String d1 = year + "-" + month + "-" + toAdd0(i) + DAY_START;
                        String d2 = year + "-" + month + "-" + toAdd0(i) + DAY_END;

                        int num = count(d1,d2,cardId,"1");
                        int num2 = count(d1,d2,cardId,"2");
                        map.put(year + "-" + month + "-" + toAdd0(i), (long) num);
                        map2.put(year + "-" + month + "-" + toAdd0(i), (long) num2);
                    }
                }else {
                    int t1 = count(year + month + MONTH_START, DateUtils.formatDateTime(new Date()), cardId, "3");
                    int t2 = count(year + month + MONTH_START, DateUtils.formatDateTime(new Date()), cardId, "4");
                    m.put("total1", (long) t1);
                    m.put("total2", (long) t2);

                    for (int i = 1; i <= d; i++) {
                        String d1 = year + "-" + month + "-" + toAdd0(i) + DAY_START;
                        String d2 = year + "-" + month + "-" + toAdd0(i) + DAY_END;

                        int num = count(d1,d2,cardId,"3");
                        int num2 = count(d1,d2,cardId,"4");
                        map.put(year + "-" + month + "-" + toAdd0(i), (long) num);
                        map2.put(year + "-" + month + "-" + toAdd0(i), (long) num2);
                    }
                }
            }else{
                if(StringUtils.isNotBlank(startAt) && StringUtils.isNotBlank(endAt)){   //-------自选时间
                    Date d1 = DateUtils.parseDate(startAt);
                    Date d2 = DateUtils.parseDate(endAt);
                    if(type.equals("0")) {
                        int t1 = count(DateUtils.formatDateTime(d1), DateUtils.formatDateTime(d2), cardId, "1");
                        int t2 = count(DateUtils.formatDateTime(d1), DateUtils.formatDateTime(d2), cardId, "2");
                        m.put("total1", (long) t1);
                        m.put("total2", (long) t2);

                        while (d1.getTime() <= d2.getTime()) {
                            String s1 = DateUtils.formatDateTime(d1);
                            Date date = DateUtils.addDate1(d1, 1);
                            String s2 = DateUtils.formatDateTime(date);

                            int num = count(s1, s2, cardId,"3");
                            int num2 = count(s1, s2, cardId,"4");

                            String s = DateUtils.formatDate(d1);
                            map.put(s, (long) num);
                            map2.put(s, (long) num2);
                        }
                    }
                }
            }
        }
        return m;
    }

    private String toAdd0(int mon){
        String m = mon+"";
        if(mon<10){
            m = "0"+m;
        }
        return m;
    }
    private int count(String d1, String d2, String cardId, String type){
        Example example = new Example(DbSms.class);
        Example.Criteria or = example.or();
        switch (type){
            case "1":
                or.andEqualTo("type","0");
                or.andEqualTo("sendCardId", cardId);
                break;
            case "2":
                or.andEqualTo("type","0");
                or.andEqualTo("toCardId",cardId);
                break;
            case "3":
                or.andEqualTo("type","1");
                or.andEqualTo("sendCardId", cardId);
                break;
            case "4":
                or.andEqualTo("type","1");
                or.andEqualTo("toCardId", cardId);
                break;
        }
        or.andBetween("createTime",d1,d2);
        return mapper.selectCountByExample(example);
    }

}
