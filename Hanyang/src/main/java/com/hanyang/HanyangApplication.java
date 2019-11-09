package com.hanyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import com.hanyang.config.filter.SitemeshFilter;
import com.hanyang.json.JacksonObjectMapper;
import com.hanyang.rest.RestClient;
import com.hanyang.rest.kakao.service.KakaoAPIClient;
import com.hanyang.rest.kakao.service.KakaoTvAPIClient;
import com.hanyang.rest.naver.service.NaverAPIClient;
import com.hanyang.rest.youtube.service.YoutubeAPIClient;

@SpringBootApplication
public class HanyangApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanyangApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<SitemeshFilter> siteMeshFilter() {
		FilterRegistrationBean<SitemeshFilter> filter = new FilterRegistrationBean<SitemeshFilter>();
		filter.setFilter(new SitemeshFilter());
		return filter;
	}
	
	@Bean
	public KakaoAPIClient kakaoAPIClient() {
		String host="https://dapi.kakao.com";
		KakaoAPIClient kakaoAPIClient = new KakaoAPIClient(objectMapper(),restClient(),host);
		return kakaoAPIClient;
	}
	@Bean
	public KakaoTvAPIClient kakaoTvAPIClient() {
		String host="https://tv.kakao.com";
		KakaoTvAPIClient kakaoTvAPIClient = new KakaoTvAPIClient(objectMapper(),restClient(),host);
		return kakaoTvAPIClient;
	}
	@Bean
	public NaverAPIClient naverAPIClient() {
		String host="https://openapi.naver.com";
		NaverAPIClient naverAPIClient = new NaverAPIClient(objectMapper(),restClient(),host);
		return naverAPIClient;
	}

	
	@Bean
	public YoutubeAPIClient youtubeAPIClient() {
		YoutubeAPIClient youtubeAPIClient = new YoutubeAPIClient();
		return youtubeAPIClient;
	}
	@Bean
	public JacksonObjectMapper objectMapper() {
		return new JacksonObjectMapper();
	}
	@Bean RestClient restClient() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setReadTimeout(5000);
		simpleClientHttpRequestFactory.setConnectTimeout(2000);
		return new RestClient(true, simpleClientHttpRequestFactory, objectMapper());
	}
	
}
