package com.doubleskyline.manage.modules.bd.task;

import com.doubleskyline.manage.modules.bd.mapper.SmsRtMapper;
import com.doubleskyline.manage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

/**
 * @Description
 * @auther SIMON
 * @date 2020/4/27
 */
@Component
public class SmsTask {

    @Autowired
    private SmsRtMapper smsRtMapper;

    @Scheduled(cron = "0 0 1 * * ?")
    public void getSixDate(){
        try {
            //获取最近六个月前的日期
            List<String> strings = TimeUtil.getliuMonth();
            String start = strings.get(0);
            String end = strings.get(5);
            smsRtMapper.insertSixDate(start,end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
