package com.hanyang.crawler.youtube.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.hanyang.crawler.kakao.service.KakaoService;
import com.hanyang.crawler.ws.RealTimeRank;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class YoutubeResource {
	@Autowired
	private KakaoService daumService;
	/*@GetMapping(value="/daum/rank",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<ServerResponse> getList() throws IOException{
        return ServerResponse.ok().body(Flux.fromIterable(daumService.getRealTimeRank()), RealTimeRank.class);
	}*/
}
