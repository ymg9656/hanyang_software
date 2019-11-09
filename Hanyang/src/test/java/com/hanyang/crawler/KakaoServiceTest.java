package com.hanyang.crawler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;

public class KakaoServiceTest {
	//@Test
	public void test3() throws IOException {
		String str="<span class=\"ico_kakaotv_new\">재생수 : </span>17,949\r\n" + 
				"                ";
		System.out.println(str.substring(str.indexOf("</span>")+7,str.length()));
		
	}
	@Test
	public void test2() throws IOException {
		String search="자동차";
		String page="1";
		String url = "https://tv.kakao.com/search/cliplinks?q=" + search + "&page=" + page;
		Document document = Jsoup.connect(url).get();
		Elements vlicpList = document.select(".list_favoritem").get(0).select(".video_item");
		List<Vclip> r = new ArrayList<Vclip>();
		for (Element el : vlicpList) {
			Vclip v = new Vclip();
			String link = el.select("a.link_itembox").get(0).attr("href");
			String title = el.select(".tit_item").get(0).html();
			String thumbnail = el.select(".thumb_box").get(0).select("img").get(0).attr("src");
			String playTime = el.select(".thumb_box").get(0).select(".mark_time").get(0).html();
			String viewCount = el.select(".info_append.info_play").get(0).html();
			//String date = el.select(".info_append").get(1).select("span").get(1).html();
			Elements dates=el.select(".info_append").get(1).select("span");
			
			String dtStr=el.select(".info_append").get(1).select("span").get(2).attr("data-raw-date");
			System.out.println("dtStr : "+dtStr);
			
			
			viewCount=viewCount.substring(viewCount.indexOf("</span>")+7, viewCount.length());
			String type = "kakao";
			v.setLink(link);
			v.setTitle(title);
			v.setThumbnail(thumbnail);
			v.setPlayTime(playTime);
			//v.setDate(date);
			v.setType(type);
			r.add(v);
		}
	}
	//@Test
	public void test() throws IOException {
		String url="https://tv.kakao.com/search/cliplinks?q=자동차&page=1";
		Document document = Jsoup.connect(url).get();
		Elements vlicpList=document.select(".list_favoritem").get(0).select(".video_item");
		List<Vclip> r = new ArrayList<Vclip>(); 
		for(Element el:vlicpList) {  
			Vclip v=new Vclip();
			String link=el.select("a.link_itembox").get(0).attr("href");
			String title=el.select(".tit_item").get(0).html();
			String thumbnail=el.select(".thumb_box").get(0).select("img").get(0).attr("src");
			String playTime=el.select(".thumb_box").get(0).select(".mark_time").get(0).html();
			String date=el.select(".info_append").get(0).select("span").get(1).html();
			String type="kakao";
			v.setLink(link);
			v.setTitle(title);
			v.setThumbnail(thumbnail);
			v.setPlayTime(playTime);
			v.setDate(date);
			v.setType(type);
			r.add(v);
		}
		System.out.println(r.size());
	}

}
