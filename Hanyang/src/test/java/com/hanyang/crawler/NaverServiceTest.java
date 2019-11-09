package com.hanyang.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class NaverServiceTest {

	//@Test
	public void imageSearchTest() throws IOException {
		//ToDo
		String url="https://search.naver.com/search.naver?where=image&sm=tab_jum&query=";
		String search="자동차";
		//Doing
		Document document = Jsoup.connect(url+search).get();
		Elements imageList=document.select(".photo_grid").get(0).select(".img_area");
		for(Element el:imageList) {
			String href=el.select(".org_view.spimg").get(0).attr("href");
			Elements ee=el.select("img");
			for(Element e:ee) {
				Attributes attrs=e.attributes();
				System.out.println(attrs.html());
				
				//System.out.println("href : "+href+" / src : "+e.attr("data-source")+" / style : "+e.attr("style"));
				
			}
			
		}
		
		//System.out.println(document.html());
		//Done
	}
	
	@Test
	public void vClipSearchTest() throws IOException {
		//ToDo 
		String url="https://tv.naver.com/search/clip?query=배틀그라운드&sort=rel&isTag=false";
		//Doing
		Document document = Jsoup.connect(url).get();
		Elements imageList=document.select("#clip_list").get(0).select(".thl_a");
		for(Element el:imageList) {
			String href=el.select(".cds_thm").get(0).attr("href");
			Element ee=el.select(".cds_thm").get(0).select("img").get(0);
			System.out.println("href : "+href+" / src : "+ee.attr("src"));
		}
	}

}
