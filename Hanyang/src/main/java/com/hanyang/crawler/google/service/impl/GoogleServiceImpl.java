package com.hanyang.crawler.google.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hanyang.crawler.google.service.GoogleService;
import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;
import com.hanyang.util.DateUtil;

@Service
public class GoogleServiceImpl implements GoogleService {

	@Override
	public List<RealTimeRank> getRealTimeRank() throws IOException {
		List<RealTimeRank> list = new ArrayList<RealTimeRank>();
		//https://trends.google.co.kr/trends/explore?date=2019-10-13%202019-10-16&geo=KR&gprop=youtube
		Document document = Jsoup.connect("https://trends.google.co.kr/trends/trendingsearches/daily/rss?geo=KR").get();
		Elements rankList = document.select("item");

		String fmt = "yyyy-MM-dd";
		for (int i = 0; i < rankList.size(); i++) {
			Element rank = rankList.get(i);
			Element dtEl = rank.getElementsByTag("pubDate").get(0);
			String dtStr = dtEl.html();
			try {
				String compare1 = DateUtil.formatChange(dtStr, "EEE, d MMM yyyy HH:mm:ss Z", fmt);
				String compare2 = DateUtil.format(new Date(), fmt);
				if (DateUtil.stringDateCompareTo(compare1, compare2, fmt) == 0) {
					// 날짜가 같을때만
					Element titleEl = rank.getElementsByTag("title").get(0);
					RealTimeRank r = new RealTimeRank();
					r.setRank(i + 1);
					r.setHtml(titleEl.html());
					list.add(r);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Image> getImageList(String search) throws IOException {
		/*
		 * { "ct": 6, "id": "N15j5_Ikg74kIM:", "isu": "v.daum.net", "itg": 0, "ity":
		 * "jpg", "oh": 296, "ou":
		 * "https://t1.daumcdn.net/news/201511/11/seoul/20151111101617941svgh.jpg",
		 * "ow": 239, "pt": "송혜민의 월드why] 곰돌이 푸가 \u0027선정적\u0027이라고? 오해와 진실 ...", "rh":
		 * "v.daum.net", "rid": "T8Fh8SntgrSEaM", "rmt": 0, "rt": 0, "ru":
		 * "https://v.daum.net/v/20151111100611244?f\u003dm", "sc": 1, "st": "Daum",
		 * "th": 250, "tu":
		 * "https://encrypted-tbn0.gstatic.com/images?q\u003dtbn:ANd9GcTiBPXcBtBPw5XtiOweuy3X19LIIH03u4jRDkSvQhfGCUSh6riSIQ",
		 * "tw": 202 }
		 */

		// ToDo
		//ijn=1&start=100
		String url = "https://google.com/search?q=" + search + "&hl=ko&source=lnms&tbm=isch&sa=X";
		// Doing
		Document document = Jsoup.connect(url).get();
		Elements el = document.select(".rg_bx.rg_di.rg_el.ivg-i");
		List<Image> list = new ArrayList<Image>();
		for (Element e : el) {
			Image i = new Image();
			//String src = e.select(".rg_l").get(0).select("img.rg_ic.rg_i").get(0).attr("src");
			
			String data_src = e.select(".rg_l").get(0).select("img.rg_ic.rg_i").get(0).attr("data-src");
			//String link = e.select(".iKjWAf.irc-nic.isr-rtc.a-no-hover-decoration").get(0).attr("href");
			String meta = e.select(".rg_meta.notranslate").get(0).html();
			JSONObject json;
			try {
				json = new JSONObject(meta);
				String title = json.getString("pt");
				String src = json.getString("tu");
				i.setLink(json.getString("ru"));
				i.setTitle(title);
				i.setMeta(meta);
			
				String thumbnail = StringUtils.hasText(src) == true ? src : data_src;
				i.setThumbnail(thumbnail);
				i.setType("google");
				list.add(i);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Vclip> getVlicpList(String search, int page) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
