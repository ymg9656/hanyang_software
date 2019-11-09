package com.hanyang.crawler.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.ws.Vclip;

public interface CrawlerService {
	List<RealTimeRank> getRealTimeRank() throws IOException;//실시간 검색어 조회 
	List<Image> getImageList(String search) throws IOException;//이미지 검색 조회
	List<Vclip> getVlicpList(String search,int page) throws IOException;//비디오 검색 조회
}
 