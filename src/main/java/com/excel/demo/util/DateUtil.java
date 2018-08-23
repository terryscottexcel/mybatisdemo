package com.excel.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String PATTER_DATE = "yyyy/MM/dd";
	private static final String PATTER_DATETIME = "yyyy/MM/dd HH:mm:ss";
	
	public static Date convertToUtilDateByFormat(String datestr,String format) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date convertToUtilDate(String datestr) {
		return convertToUtilDateByFormat(datestr,PATTER_DATE);
	}
	
	public static Date convertToUtilDatetime(String datestr) {
		return convertToUtilDateByFormat(datestr,PATTER_DATETIME);
	}
	
	public static java.sql.Date convertToSqlDate(Date utildate) {
		java.sql.Date date = null;
		try {
			if(utildate!=null) {
				date = new java.sql.Date(utildate.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String convertToStringDate(Date utildate) {
		String datestr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTER_DATE);
		datestr = sdf.format(utildate);
		
		return datestr;
	}
	
	public static String convertToStringDatetime(Date utildate) {
		String datestr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(PATTER_DATETIME);
		datestr = sdf.format(utildate);
		
		return datestr;
	}
}
