package com.hanyang.rest.naver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanyang.rest.APIClientSupport;
import com.hanyang.rest.RestClient;
import com.hanyang.rest.kakao.domain.KakaoImageDocument;

public class NaverAPIClient extends APIClientSupport {
	private static final Logger logger = LoggerFactory.getLogger(NaverAPIClient.class);

	private String X_Naver_Client_Id="8el9io25n4wSYHiSMseT";
	private String X_Naver_Client_Secret="1B2pEoEny_";
	public NaverAPIClient(ObjectMapper objectmapper, RestClient restClient, String host) {
		super(objectmapper, restClient, host);
	}
	

	public NaverAPIResult getImageSearch(String search, int page) {
		/*
		 * query 검색을 원하는 질의어 O String sort 결과 문서 정렬 방식 X (accuracy) accuracy (정확도순) or
		 * recency (최신순) page 결과 페이지 번호 X(기본 1) 1-50 사이 Integer size 한 페이지에 보여질 문서의 개수
		 * X(기본 10) 1-50 사이 Integer
		 */
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder("/v1/search/image");
		uriBuilder.queryParam("query", search);
		uriBuilder.queryParam("start", String.valueOf(page));
		uriBuilder.queryParam("display", String.valueOf(100));

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", X_Naver_Client_Id);
		headers.set("X-Naver-Client-Secret", X_Naver_Client_Secret);
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		try {
			String uri = uriBuilder.build().toUriString();
			logger.debug("uri:{}", uri);
			ResponseEntity<NaverAPIResult> response = restClient.exchange(uri, HttpMethod.GET,
					httpEntity, new ParameterizedTypeReference<NaverAPIResult>() {
					});

			if (logger.isDebugEnabled()) {
				logger.debug("response:{}", response);
			}
			return response.getBody();
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
