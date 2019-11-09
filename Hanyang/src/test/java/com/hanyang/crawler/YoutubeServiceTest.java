package com.hanyang.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class YoutubeServiceTest {

	@Test
	public void test() throws IOException { 
		//Document document2 = Jsoup.connect("https://trends.google.co.kr/trends/explore").get();
		//System.out.println(document2.html());
		
		Document document = Jsoup.connect("https://trends.google.co.kr/trends/trendingsearches/daily?geo=KR").get();
		Element el=document.tagName("ytd-page-manager");
		Elements list=el.select("h3.yt-lockup-title");
		System.out.println(list.size());
		
		Document document2 = Jsoup.connect("https://www.youtube.com/feed/trending").get();
		Element el2=document2.tagName("ytd-page-manager");
		Elements rankList=el2.select("h3.yt-lockup-title");
		System.out.println(rankList.size());
		
		for(Element e : list) {
			//System.out.println(e.select("a").attr("title"));	
		} 
		//System.out.println(list.size());
		
		
		/*Elements all=document.getAllElements();
		for(Element e:all) {
			System.out.println("className : "+e.className()+" / data : "+e.data());
			
		}
		System.out.println(all.size());*/ 
		/*Elements list=document.select("div.text-wrapper.style-scope.ytd-video-renderer");
		System.out.println(list.size());
		*/
		
		document.body();
		System.out.println("zz");
		
	}

}
