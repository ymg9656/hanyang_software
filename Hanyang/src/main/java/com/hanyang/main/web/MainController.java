package com.hanyang.main.web;

import com.hanyang.crawler.google.service.GoogleService;
import com.hanyang.crawler.kakao.service.KakaoService;
import com.hanyang.crawler.naver.service.NaverService;
import com.hanyang.crawler.ws.RealTimeRank;
import com.hanyang.crawler.youtube.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
	@Autowired
	private NaverService naverService;
	@Autowired
	private KakaoService daumService;
	@Autowired
	private GoogleService googleService;
	@Autowired
	private YoutubeService youtubeService;
	
	private static List<RealTimeRank> daumRealTimeRankList;
	private static List<RealTimeRank> naverRealTimeRankList;
	private static List<RealTimeRank> googleRealTimeRankList;
	private static List<RealTimeRank> youtubeRealTimeRankList;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) throws IOException {
		if(daumRealTimeRankList==null) {//개발용 코드 
			List<RealTimeRank> daumRealTimeRankList=daumService.getRealTimeRank();
			MainController.daumRealTimeRankList=daumRealTimeRankList;
		}
		model.addAttribute("daumRealTimeRankList", daumRealTimeRankList);
		
		
		if(naverRealTimeRankList==null) {//개발용 코드
			List<RealTimeRank> naverRealTimeRankList=naverService.getRealTimeRank();
			MainController.naverRealTimeRankList=naverRealTimeRankList;
		}
		model.addAttribute("naverRealTimeRankList", naverRealTimeRankList);

		if(googleRealTimeRankList==null) {//개발용 코드
			List<RealTimeRank> googleRealTimeRankList=googleService.getRealTimeRank();
			MainController.googleRealTimeRankList=googleRealTimeRankList;
		}
		model.addAttribute("googleRealTimeRankList", googleRealTimeRankList);
		
		if(youtubeRealTimeRankList==null) {//개발용 코드
			List<RealTimeRank> youtubeRealTimeRankList=youtubeService.getRealTimeRank();
			MainController.youtubeRealTimeRankList=youtubeRealTimeRankList;
		}
		model.addAttribute("youtubeRealTimeRankList", youtubeRealTimeRankList);
		
		
		return "index";
	} 
}
