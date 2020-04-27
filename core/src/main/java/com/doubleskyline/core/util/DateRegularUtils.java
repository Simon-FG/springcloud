package com.doubleskyline.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateRegularUtils {
	private static final String  first ="[0-9]{4}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}";// yyyyMMddHHmmss	
	private static final String  second ="[0-9]{4}[0-9]{2}[0-9]{2}";// yyyyMMdd
	private static final String  third ="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";// yyyy-MM-dd HH:mm:ss
	private static final String fourth="[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";// yyyy-MM-dd HH:mm
	private static final String fifth ="[0-9]{4}-[0-9]{2}-[0-9]{2}";// yyyy-MM-dd
	private static final String[] regular= {first,second,third,fourth,fifth};
	
	private static final String  firstFormat ="yyyyMMddHHmmss";// yyyyMMddHHmmss	
	private static final String  secondFormat ="yyyyMMdd";// yyyyMMdd
	private static final String  thirdFormat ="yyyy-MM-dd HH:mm:ss";// yyyy-MM-dd HH:mm:ss
	private static final String fourthFormat="yyyy-MM-dd HH:mm";// 
	private static final String fifthFormat ="yyyy-MM-dd";//
	private static final String[] regularFormat= {firstFormat,secondFormat,thirdFormat,fourthFormat,fifthFormat};

	public static void main(String[] args)  {
		String newsTime = "2016-04-16 09:31 来源：张家口新闻网";
		
		String time = getDatetimeFormat(newsTime).toString();
		System.out.println(getDatetimeFormat(time));
	}

	public static Long getDatetimeFormat(String date)  {
		try {
		for(int i=0 ;i<regular.length;i++) {			
			String time =regularCheck(date,regular[i]);
			if(null !=time ) {
				  Calendar c = Calendar.getInstance();
		          c.setTime(new SimpleDateFormat(regularFormat[i]).parse(time));
				Long a =c.getTimeInMillis();
				return a;
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0L;

		
	}

	
	public static String regularCheck(String date, String rules) {
		Pattern p = Pattern.compile(rules);
		Matcher m = p.matcher(date);
		if (m.find()) {
			if(m.group().length()>0) {
			return m.group();
			}
		}
		return null;
	}

	
	
	
	
}
