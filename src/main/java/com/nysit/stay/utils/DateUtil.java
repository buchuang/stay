package com.nysit.stay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateToString(Date date){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dString=simpleDateFormat.format(date);
		return dString;
	}
	public static String getCurrentTime(){
		return DateUtil.dateToString(new Date());
	}
}
