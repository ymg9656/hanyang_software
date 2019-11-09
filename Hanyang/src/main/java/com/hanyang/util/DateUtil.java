package com.hanyang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {
	
	
	public static String formatChange(String dt,String fromFmt,String toFmt) throws ParseException {
		SimpleDateFormat fromFormat=new SimpleDateFormat(fromFmt,Locale.US);
		SimpleDateFormat toformat=new SimpleDateFormat(toFmt,Locale.US);
		return toformat.format(fromFormat.parse(dt));
	}
	public static Date formatChangeAndParse(String dt,String fromFmt,String toFmt) throws ParseException {
		SimpleDateFormat fromFormat=new SimpleDateFormat(fromFmt,Locale.US);
		SimpleDateFormat toformat=new SimpleDateFormat(toFmt,Locale.US);
		return getStringToDate(toformat.format(fromFormat.parse(dt)),toFmt);
	}
	public static String format(Date dt,String fmt) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat(fmt,Locale.US);
		return format.format(dt);
	}
	public static Date parse(String dt,String fmt) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat(fmt,Locale.US);
		return format.parse(dt);
	}
	public static int stringDateCompareTo(String compare1,String compare2,String fmt) throws ParseException {
		Date dt1=parse(compare1, fmt);
		Date dt2=parse(compare2, fmt);
		return dt1.compareTo(dt2);
	}
	public static Date getStringToDate(String dt,String fmt) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat(fmt,Locale.US);
		return format.parse(dt);
	}
	public static String getFewDaysAgo(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date now = new Date();
	    Date secondDate = sdf.parse(date);
	 
	    long diffInMillies = Math.abs(now.getTime() - secondDate.getTime());
	    
	    long dayDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    long hourDiff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    long minutesDiff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	       
	    if(dayDiff/365>=1) {
	    	System.out.println("divYearDiff : "+dayDiff/365);
	    	return (dayDiff/365)+"년전";
	    }else if(dayDiff/30 >=1) {
	    	System.out.println("divMonthDiff : "+dayDiff/30);
	    	return (dayDiff/30)+"개월전";
	    }else if(dayDiff/7 >=1) {
	    	System.out.println("divWeekDiff : "+dayDiff/7);
	    	return (dayDiff/7)+"주전";
	    }else if(dayDiff >=1) {
	    	System.out.println("dayDiff : "+dayDiff);
	    	return (dayDiff)+"일전";
	    }else if(hourDiff >=1) {
	    	System.out.println("hourDiff : "+hourDiff);
	    	return hourDiff+"시간전";
	    }else if(minutesDiff >=1) { 
	    	System.out.println("minutesDiff : "+minutesDiff);
	    	return minutesDiff+"분전";
	    }
		return "방금";
	}
}
