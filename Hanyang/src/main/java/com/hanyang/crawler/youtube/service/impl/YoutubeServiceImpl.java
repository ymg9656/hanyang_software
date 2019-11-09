package com.hanyang.crawler.youtube.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;
import com.hanyang.crawler.youtube.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService{

	@Override
	public List<RealTimeRank> getRealTimeRank() throws IOException {
		List<RealTimeRank> list=new ArrayList<RealTimeRank>();
		//TODO
		/*
		 * 일일 트래픽이 제한있는듯.. 429 에러 발생하기 때문에 한번만 로드하고 메모리에 올려둔다.
		 * */
		//Document document2 = Jsoup.connect("https://trends.google.co.kr/trends/explore").get();
		Document document = Jsoup.connect("https://www.youtube.com/feed/trending").get();
		System.out.println(document.html());
		Element el=document.tagName("ytd-page-manager");
		Elements rankList=el.select("h3.yt-lockup-title");
		System.out.println(rankList.size());
		for(int i = 0; i<10;i++) {
			Element rank=rankList.get(i); 
			RealTimeRank r=new RealTimeRank();
			r.setHtml(rank.select("a").attr("title"));
			list.add(r);
		}
		return list;
	}

	@Override
	public List<Image> getImageList(String search) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vclip> getVlicpList(String search, int page) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	
}
