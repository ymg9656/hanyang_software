package com.hanyang.search.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hanyang.crawler.google.service.GoogleService;
import com.hanyang.crawler.kakao.service.KakaoService;
import com.hanyang.crawler.naver.service.NaverService;
import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.youtube.service.YoutubeService;
import com.hanyang.rest.kakao.domain.KakaoImageDocument;
import com.hanyang.rest.kakao.service.KakaoAPIClient;
import com.hanyang.rest.kakao.service.KakaoAPIResult;
import com.hanyang.rest.naver.service.NaverAPIClient;
import com.hanyang.rest.naver.service.NaverAPIResult;
import com.hanyang.rest.youtube.domain.YoutubeVclip;
import com.hanyang.rest.youtube.service.YoutubeAPIClient;
import com.hanyang.rest.youtube.service.YoutubeAPIResult;


@RestController
@RequestMapping("/rest/search")
public class SearchResource {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);
	
	
	@Autowired
	private NaverService naverService;
	@Autowired
	private KakaoService daumService;
	@Autowired
	private GoogleService googleService;
	@Autowired
	private YoutubeService youtubeService;
	
	
	@Autowired
	private KakaoAPIClient kakaoAPIClient;
	
	@Autowired
	private YoutubeAPIClient youtubeAPIClient;
	
	@Autowired
	private NaverAPIClient naverAPIClient;
	
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public @ResponseBody List<Image> searchImage(
			@RequestParam(required=true)String q,
			@RequestParam(required=true) int page, 
			Model model) throws IOException {
		List<Image> restImageList=new ArrayList<Image>();
		
		KakaoAPIResult<KakaoImageDocument> kakaoResult=kakaoAPIClient.getImageSearch(q, page);
		if(kakaoResult!=null&&kakaoResult.getDocuments()!=null) {
			List<Image> r=kakaoResult.getDocuments().stream()
				    .map(e -> (Image) e)
				    .collect(Collectors.toList());
			restImageList.addAll(r); 
		}
		NaverAPIResult naverResult=naverAPIClient.getImageSearch(q, page);
		if(naverResult!=null&&naverResult.getItems()!=null) {
			List<Image> r=naverResult.getItems().stream()
				    .map(e -> (Image) e)
				    .collect(Collectors.toList());
			restImageList.addAll(r);
		}
		Collections.shuffle(restImageList);
		return restImageList;
	} 
	
	
}
