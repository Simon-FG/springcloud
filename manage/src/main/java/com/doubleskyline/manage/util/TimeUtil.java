package com.doubleskyline.manage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @auther SIMON
 * @date 2020/4/17
 */
public class TimeUtil {

    /**
     * 获取最近六个月 年月
     * @return
     * @throws ParseException
     */
    public static List<String> getliuMonth() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -4);
        String before_six = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH);//六个月前
        List<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(before_six));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(sdf.parse(sdf.format(new Date())));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }
}
