package com.hanyang.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanyang.json.JacksonObjectMapper;
import com.hanyang.util.DateUtil;

public class GoogleServiceTest {
	@Test
	public void trends() throws IOException, JSONException, ParseException {
		String fromDt=DateUtil.format(new Date(), "yyyy-MM-dd");
		String toDt=DateUtil.format(DateUtils.addDays(new Date(), -4), "yyyy-MM-dd");
		
		
		Document document = Jsoup.connect("https://trends.google.co.kr/trends/explore?date="+fromDt+" "+toDt+"&geo=KR&gprop=youtube").get();
		System.out.println(document.html()); 
		/*
		Elements el=document.select(".rg_bx.rg_di.rg_el.ivg-i");
		for(Element e:el) {
			String src=e.select(".rg_l").get(0).select("img.rg_ic.rg_i").get(0).attr("src");
			String data_src=e.select(".rg_l").get(0).select("img.rg_ic.rg_i").get(0).attr("data-src");
			
			String link=e.select(".iKjWAf.irc-nic.isr-rtc.a-no-hover-decoration").get(0).attr("href");
			String linkT=e.select(".rg_meta.notranslate").get(0).html();
			JSONObject json=new JSONObject(linkT);
			String title=json.getString("pt");
			String src2=json.getString("tu");
			
			
			//System.out.println("title : "+title+" / link : "+json.getString("ru"));
			System.out.println("title : "+title+" / src : "+src2+" / data_src : "+data_src);
			
			
		}
	*/	
		//System.out.println(document.html());
		//Done
	}
	
	
	//@Test
	public void imageSearchTest() throws IOException, JSONException {
		//ToDo
		String url="https://www.google.com/search?q=곰돌이&hl=ko&source=lnms&tbm=isch&sa=X";
		//Doing
		Document document = Jsoup.connect(url).get();
		Elements el=document.select(".rg_bx.rg_di.rg_el.ivg-i");
		for(Element e:el) {
			String src=e.select(".rg_l").get(0).select("img.rg_ic.rg_i").get(0).attr("src");
			String data_src=e.select(".rg_l").get(0).select("img.rg_ic.rg_i").get(0).attr("data-src");
			
			String link=e.select(".iKjWAf.irc-nic.isr-rtc.a-no-hover-decoration").get(0).attr("href");
			String linkT=e.select(".rg_meta.notranslate").get(0).html();
			JSONObject json=new JSONObject(linkT);
			String title=json.getString("pt");
			String src2=json.getString("tu");
			
			
			//System.out.println("title : "+title+" / link : "+json.getString("ru"));
			System.out.println("title : "+title+" / src : "+src2+" / data_src : "+data_src);
			
			
		}
		
		//System.out.println(document.html());
		//Done
	}
}
