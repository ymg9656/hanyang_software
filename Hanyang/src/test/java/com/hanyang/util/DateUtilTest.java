package com.hanyang.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class DateUtilTest {

	//@Test
	public void test() throws IOException, ParseException {
		Document document = Jsoup.connect("https://trends.google.co.kr/trends/trendingsearches/daily/rss?geo=KR").get();
		Elements rankList = document.select("item");
		for(int i = 0; i<rankList.size();i++) {
			Element rank=rankList.get(i);
			Element dtEl=rank.getElementsByTag("pubDate").get(0);
			String dtStr=dtEl.html();
			String fmt="yyyy-MM-dd";
			String compare1=DateUtil.formatChange(dtStr, "EEE, d MMM yyyy HH:mm:ss Z", fmt);
			String compare2=DateUtil.format(new Date(), fmt);
			System.out.println("compare1 : "+compare1+" / compare2 : "+compare2+" / "+DateUtil.stringDateCompareTo(compare1, compare2, fmt));
		}
	}
	@Test
	public void test2() throws IOException, ParseException {
		Integer duration=206;
		String h=duration/3600 == 0 ? null : ""+(duration/3600);
		String m=(duration % 3600 / 60) == 0 ? "00" : ""+(duration % 3600 / 60);
		m=m.length() == 1 ? "0"+m : m;
		String s=(duration % 3600 % 60) == 0 ? "00" : ""+(duration % 3600 % 60);
		s=s.length() == 1 ? "0"+s : s;
		String r= h == null ? m+":"+s : h+":"+m+":"+s; 
		System.out.println(r);
	}
	

	//@Test
	public void test1() throws IOException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date now = sdf.parse("2019-10-18 14:33:50");
	    Date secondDate = sdf.parse("2018-11-16 14:32:30");
	 
	    long diffInMillies = Math.abs(now.getTime() - secondDate.getTime());
	    
	    long dayDiff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    System.out.println("divYearDiff : "+dayDiff/365);
	    System.out.println("divMonthDiff : "+dayDiff/30);
	    System.out.println("divWeekDiff : "+dayDiff/7);
	    System.out.println("dayDiff : "+dayDiff);
	    long hourDiff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    System.out.println("hourDiff : "+hourDiff);
	    long minutesDiff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    System.out.println("minutesDiff : "+minutesDiff);
	    long secondsDiff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    System.out.println("secondsDiff : "+secondsDiff);
		
	}

}
