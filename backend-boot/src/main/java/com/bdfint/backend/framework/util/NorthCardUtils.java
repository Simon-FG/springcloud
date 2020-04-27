package com.bdfint.backend.framework.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NorthCardUtils {
	
	public static String requestNumber = "";
	/**
	 * 生成年月日十分秒+六位随机码
	 * @return
	 */
	public static String getCurrentYMDHm() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		return simple.format(new Date())+ (int)((Math.random()*9+1)*10000);
	}
	
	/***
	 * 工具用户ID+日期(年月日时分秒)+顺序号6位生成
	 * @throws UnsupportedEncodingException 
	 */
	public static String getRequestCardNumber(String type){
		if(type != null && !type.equals("") && type.equals("1")){
			type = "KSQ";//北斗卡申请
		}else if(type != null && !type.equals("") && type.equals("2")){
			type="SB";//设备购买
		}else if(type != null && !type.equals("") && type.equals("3")){
			type="ZZ";//增值业务
		}else if(type != null && !type.equals("") && type.equals("4")){
			type="ZW";//转网业务
		}else{
			type="QT";//其他业务
		}
		requestNumber = type + getCurrentYMDHm();
		return requestNumber;
	}

}
