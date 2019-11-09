package com.hanyang.search.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanyang.crawler.google.service.GoogleService;
import com.hanyang.crawler.kakao.service.KakaoService;
import com.hanyang.crawler.naver.service.NaverService;
import com.hanyang.crawler.ws.Image;
import com.hanyang.crawler.ws.Vclip;
import com.hanyang.rest.kakao.domain.KakaoImageDocument;
import com.hanyang.rest.kakao.domain.KakaoTv;
import com.hanyang.rest.kakao.service.KakaoAPIClient;
import com.hanyang.rest.kakao.service.KakaoAPIResult;
import com.hanyang.rest.kakao.service.KakaoTvAPIClient;
import com.hanyang.rest.kakao.service.KakaoTvAPIResult;
import com.hanyang.rest.naver.service.NaverAPIClient;
import com.hanyang.rest.naver.service.NaverAPIResult;
import com.hanyang.rest.youtube.domain.YoutubeVclip;
import com.hanyang.rest.youtube.service.YoutubeAPIClient;
import com.hanyang.rest.youtube.service.YoutubeAPIResult;


@Controller
@RequestMapping("/search")
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	
	@Autowired
	private NaverService naverService;
	@Autowired
	private KakaoService kakaoService;
	@Autowired
	private GoogleService googleService;
	
	
	@Autowired
	private KakaoAPIClient kakaoAPIClient;
	@Autowired
	private KakaoTvAPIClient kakaoTvAPIClient;
	
	@Autowired
	private YoutubeAPIClient youtubeAPIClient;
	
	@Autowired
	private NaverAPIClient naverAPIClient;
	
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String searchImage(@RequestParam(required=false,defaultValue="")String q
			,@RequestParam(required=false,defaultValue="1")int page
			,Model model) throws IOException {
			
		if(!StringUtils.hasText(q)) {
			return "search/imageList";
		}
		
		List<Image> restImageList=new ArrayList<Image>();
			restImageList.addAll(googleService.getImageList(q));
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
			model.addAttribute("q", q);
			model.addAttribute("page", page);
			model.addAttribute("imageList", restImageList);
		return "search/imageList";
	} 
	
	@RequestMapping(value = "/vclip", method = RequestMethod.GET)
	public String searchVclip(
			@RequestParam(required=false,defaultValue="")String q
			,@RequestParam(required=false,defaultValue="1")int page 
			,Model model) throws IOException {
		if(!StringUtils.hasText(q)) {
			return "search/vclipList";
		}
		YoutubeAPIResult<YoutubeVclip> youtubeAPIResult=youtubeAPIClient.getVclipSearch(q, ""+page);
		if(youtubeAPIResult!=null&&youtubeAPIResult.getItems()!=null) {
			model.addAttribute("youtubeNextPageToken", youtubeAPIResult.getNextPageToken());
			model.addAttribute("youtubeVclipList", youtubeAPIResult.getItems());
		}

		List<Vclip> kakaoVclipList=kakaoService.getVlicpList(q, page);
		model.addAttribute("kakaoVclipList", kakaoVclipList);
		
		List<Vclip> naverVclipList=naverService.getVlicpList(q, page);
		model.addAttribute("naverVclipList", naverVclipList);
		
		model.addAttribute("q", q);
		model.addAttribute("page", page);
		return "search/vclipList";
	} 
	@RequestMapping(value = "/{pageType}/contents/vclip", method = RequestMethod.GET)
	public String  searchVclipPageing(
			@PathVariable String pageType,
			@RequestParam(required=true)String q,
			@RequestParam(required=true) String page, 
			Model model) throws IOException {
		if(!StringUtils.hasText(q)) {
			return null;
		}
		if("y".equals(pageType)) {
			YoutubeAPIResult<YoutubeVclip> youtubeAPIResult=youtubeAPIClient.getVclipSearch(q, ""+page);	
			if(youtubeAPIResult!=null&&youtubeAPIResult.getItems()!=null) {
				model.addAttribute("youtubeNextPageToken", youtubeAPIResult.getNextPageToken());
				model.addAttribute("youtubeVclipList", youtubeAPIResult.getItems());
			} 
		}else if("n".equals(pageType)) {
			List<Vclip> naverVclipList=naverService.getVlicpList(q, Integer.parseInt(page));
			model.addAttribute("naverVclipList", naverVclipList);
			model.addAttribute("page", page);
		}else if("k".equals(pageType)) {
			KakaoTvAPIResult<KakaoTv> kakaoTvResult=kakaoTvAPIClient.getCrawlerVclipSearch(q, Integer.parseInt(page));
			if(kakaoTvResult!=null&&kakaoTvResult.getList()!=null) {
				model.addAttribute("kakaoVclipList", kakaoTvResult.getList());
				model.addAttribute("page", page);
			} 
		}
		model.addAttribute("pageType", pageType);
		return "search/contents/vclip";
	} 
	
	
}
