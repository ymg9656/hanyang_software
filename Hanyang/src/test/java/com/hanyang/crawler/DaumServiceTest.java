package com.hanyang.crawler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;

public class DaumServiceTest {
	@Test
	public void test2() throws IOException {
		List<RealTimeRank> list = new ArrayList<RealTimeRank>();
		Document document = Jsoup.connect("https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=실시간 이슈 검색어").get();
		Elements rankList = document.select("#ratIssueColl").get(0).select(".roll_rank");
		for (int i = 0; i < 10; i++) {
			Element rank = rankList.get(i);
			RealTimeRank r = new RealTimeRank();
			r.setRank(i + 1);
			System.out.println(rank.select(".keyword_rank").get(0).select("a").html());
			r.setHtml(rank.select(".keyword_rank").get(0).html());
			list.add(r);
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
