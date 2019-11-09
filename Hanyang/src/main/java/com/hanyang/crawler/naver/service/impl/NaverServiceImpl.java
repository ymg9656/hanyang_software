package com.hanyang.crawler.naver.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.hanyang.crawler.naver.service.NaverService;
import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;
@Service
public class NaverServiceImpl implements NaverService{

	@Override
	public List<RealTimeRank> getRealTimeRank() throws IOException {
		List<RealTimeRank> list=new ArrayList<RealTimeRank>();
		Document document = Jsoup.connect("https://datalab.naver.com/keyword/realtimeList.naver?where=main").get();
		Elements rankList = document.select(".rank_list").get(0).select(".list");
		for(int i = 0; i<10;i++) {
			Element rank=rankList.get(i); 
			Elements ss=rank.select(".list_area");
			RealTimeRank r=new RealTimeRank();
			r.setRank(i+1);
			r.setHtml(ss.get(0).select(".title").get(0).html());
			list.add(r);
		}
		return list;
	}

	@Override
	public List<Image> getImageList(String search) throws IOException {
		return null;
	}


	@Override
	public List<Vclip> getVlicpList(String search, int page) throws IOException {
		String url="https://tv.naver.com/search/clip?query="+search+"&sort=rel&page="+page+"&isTag=false";
		Document document = Jsoup.connect(url).get();
		Elements imageList=document.select("#clip_list").get(0).select(".thl_a");
		List<Vclip> r = new ArrayList<Vclip>();
		for(Element el:imageList) {
			Vclip v=new Vclip();
			String link=el.select(".cds_thm").get(0).attr("href");
			String title=el.select(".cds_thm").get(0).attr("title");
			String thumbnail=el.select(".cds_thm").get(0).select("img").get(0).attr("src");
			String playTime=el.select(".cds_thm").get(0).select("span.tm_b").get(0).html();
			String date=el.select(".inner").get(0).select(".ch_txt").get(0).select(".time").get(0).html();
			String viewCount=el.select(".inner").get(0).select(".cds_ifc.cnp").get(0).html();
			viewCount=viewCount.substring(viewCount.indexOf("</span>")+7, viewCount.length());
			
			v.setLink(link);
			v.setTitle(title);
			v.setThumbnail(thumbnail);
			v.setPlayTime(playTime);
			v.setDate(date);
			v.setViewCount(viewCount);
			v.setType("naver");
			r.add(v);
		}
		return r;
	}

	

}
