package com.hanyang.crawler.kakao.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.hanyang.crawler.kakao.service.KakaoService;
import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;
import com.hanyang.util.DateUtil;

@Service
public class KakaoServiceImpl implements KakaoService {

	@Override
	public List<RealTimeRank> getRealTimeRank() throws IOException {
		List<RealTimeRank> list = new ArrayList<RealTimeRank>();
		Document document = Jsoup.connect("https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=실시간 이슈 검색어").get();
		Elements rankList = document.select("#ratIssueColl").get(0).select(".roll_rank");
		for (int i = 0; i < 10; i++) {
			Element rank = rankList.get(i);
			RealTimeRank r = new RealTimeRank();
			r.setRank(i + 1);
			r.setHtml(rank.select(".keyword_rank").get(0).select("a").html());
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
			String date = el.select(".info_append").get(1).select("span").get(2).attr("data-raw-date");
			try {
				date=DateUtil.getFewDaysAgo(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			viewCount=viewCount.substring(viewCount.indexOf("</span>")+7, viewCount.length());

			
			String type = "kakao";
			v.setLink(link);
			v.setTitle(title);
			v.setThumbnail(thumbnail);
			v.setPlayTime(playTime);
			v.setDate(date);
			v.setType(type);
			v.setViewCount(viewCount);
			r.add(v);
		}
		return r;
	}

}
